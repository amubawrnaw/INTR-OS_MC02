package Locks.Model;

import gui_locks.GUIFrame;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Train extends Thread{
	public int max_seat_count;
	public int occupied_seats;
        public GUIFrame gf;
	public Object passenger_lock;
	public Object train_full;
	public Object new_train;

	public int current_station;

	private int direction;
	public int id;

	private static int train_count = 0;
	private static int train_id = 0;
	
	public static Station[] stations;

	public volatile boolean running;
	public boolean valid;

	public Train(int seat_count, int direction, GUIFrame gf){
                this.gf = gf;
		max_seat_count = seat_count;
		this.direction = direction;
		current_station = 0;
		passenger_lock = new Object();
		train_full = new Object();
		running = true;
		valid = true;
		if(train_count >= 16){
			System.out.println("Train not added, too many trains!");
			valid = false;
		}else{
			id = train_id%16;
			train_count++;
			train_id++;
		}
	}



	@Override
	public void run(){
		if(valid){
                        gf.activateTrain(id);
			long start = System.currentTimeMillis();
			while(running){
				boolean has_entered = false;
				while(stations[current_station].isOccupied()){
					synchronized (stations[current_station].train_lock){
						System.out.println("Train " + id + " is waiting for Station " + current_station + " to open.");
						try{
							stations[current_station].train_lock.wait();
						}catch(Exception e){
							e.printStackTrace();
						}		
					}
					boolean b = stations[current_station].station_load_train(this);
					if(b) {
						has_entered = true;
						break;
					}
				}
                                try{
					sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}
				//load train if not loaded awhile ago
				if(!has_entered){
					stations[current_station].station_load_train(this);
				}
                                gf.moveToNextStation(id);
                                System.out.println("Train " + id + " is now on station " + current_station + ", passengers: " + occupied_seats);
                                
                                
				//notify passengers train has arrived, passengers can now leave.
				synchronized(passenger_lock){
					passenger_lock.notifyAll();
				}
                                
				try{
                                        sleep(500);
				}catch(Exception e){
					e.printStackTrace();
				}

				
				//check if train is full
				if(!isFull()){
					//notify that people can board.
					synchronized(stations[current_station].train_boardable){
						stations[current_station].train_boardable.notifyAll();
					}
				}

				//move to next station, loops from 0-7
				int temp = current_station;
				stations[current_station].station_leave_train();
                                gf.moveToNextStation(id);
				if(Passenger.actual_passenger_count == 0 && current_station == 7 && occupied_seats == 0){
					running = false;
					System.out.println("Train " + id + " has left the Simulator");
					gf.leaveSimulation(id);
					break;
				}
				current_station = (current_station+1)%8;
				//System.out.println(occupied_seats + " " + max_seat_count + " " + stations[temp].hasPassengers());
				
				//release the lock on the unboarded passengers if theres any.
				if(isFull() && stations[temp].hasPassengers()){
                                    //System.out.println("release");
                                    synchronized(stations[temp].train_boardable){
                                            stations[temp].train_boardable.notifyAll();
                                    }
				}
			}
			//remove train
			train_count--;
			long end = System.currentTimeMillis();
					System.out.println(end-start + " milliseconds");
			
		}
	}

	public synchronized boolean board(Passenger p){
            try {
                sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
		if(!isFull()){
			occupied_seats++;
                        System.out.println("Passenger " + p.pass_id + " has boarded train " + id + " destination : " + p.destination_station);
                        gf.setPassenger(id, occupied_seats);
			return true;
		}
		//System.out.println("Passenger " + p.pass_id + " failed to board train " + id);
		return false;
	}
	public synchronized boolean isFull(){
		return (max_seat_count == occupied_seats);
	}
	public synchronized void leave(){
                try {
                    sleep(100);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
		occupied_seats--;
                gf.setPassenger(id, occupied_seats);
	}

	public static void initStations(){
		stations = new Station[8];
		for(int i = 0 ; i < 8 ; i++){
			Station temp = new Station();
			stations[i] = temp;
		}
	}
}