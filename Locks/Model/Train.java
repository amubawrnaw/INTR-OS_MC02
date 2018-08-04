package Model;

import java.util.*;

public class Train extends Thread{
	public ArrayList<Passenger> passengers;
	public int max_seat_count;
	private int occupied_seats;
	public Object passenger_lock;
	public Object train_full;
	public int current_station;

	private int direction;
	public int id;
	
	public static Station[] stations;

	public volatile boolean running;

	public Train(int seat_count, int direction, int t_id){
		passengers = new ArrayList<>();
		max_seat_count = seat_count;
		id = t_id;
		this.direction = direction;
		current_station = 0;
		passenger_lock = new Object();
		train_full = new Object();
		running = true;
	}

	@Override
	public void run(){
		while(running){
			try{
				sleep(300);
			}catch(Exception e){
				e.printStackTrace();
			}
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

			//load train if not loaded awhile ago
			if(!has_entered){
				stations[current_station].station_load_train(this);
			}
			//notify passengers train has arrived, passengers can now leave.
			synchronized(passenger_lock){
				passenger_lock.notifyAll();
			}
			
			System.out.println("Train " + id + " is now on station " + current_station + ", passengers: " + occupied_seats);
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
			if(Passenger.actual_passenger_count == 0 && current_station == 7){
				running = false;
				System.out.println("Train " + id + " has left the Simulator");
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
	}

	public synchronized boolean board(Passenger p){
		if(!isFull()){
			occupied_seats++;
			System.out.println("Passenger " + p.pass_id + " has boarded train " + id);
			return true;
		}
		//System.out.println("Passenger " + p.pass_id + " failed to board train " + id);
		return false;
	}
	public synchronized boolean isFull(){
		return (max_seat_count == occupied_seats);
	}
	public synchronized void leave(){
		occupied_seats--;
	}

	public static void initStations(){
		stations = new Station[8];
		for(int i = 0 ; i < 8 ; i++){
			Station temp = new Station();
			stations[i] = temp;
		}
	}
}