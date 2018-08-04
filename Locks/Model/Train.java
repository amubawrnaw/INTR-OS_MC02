package Model;

import java.util.*;

public class Train extends Thread{
	public ArrayList<Passenger> passengers;
	public int max_seat_count;
	private int occupied_seats;
	public Object passenger_lock;
	public int current_station;

	private int direction;
	private int id;
	
	public static Station[] stations;

	public volatile boolean running;

	public Train(int seat_count, int direction, int t_id){
		passengers = new ArrayList<>();
		max_seat_count = seat_count;
		id = t_id;
		this.direction = direction;
		current_station = 0;
		passenger_lock = new Object();
		running = true;
	}

	@Override
	public void run(){
		while(running){
			
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
				if(b){
					
			
					//notify passengers train has arrived, passengers can now leave.
					passenger_lock.notifyAll();
					break;
				} 
			}
			
			System.out.println("Train " + id + " is now on station " + current_station);
			//check if train is full
			if(occupied_seats < max_seat_count){
				//notify that people can board.
				synchronized(stations[current_station].train_boardable){
					stations[current_station].train_boardable.notifyAll();
				}
			}

			//move to next station, loops from 0-7
			stations[current_station].station_leave_train();
			current_station = (current_station+1)%8;
		}
	}

	public synchronized boolean board(Passenger p){
		if(max_seat_count<occupied_seats){
			occupied_seats++;
			System.out.println("Passenger " + p.pass_id + " has boarded train " + id);
			return true;
		}
		System.out.println("Passenger " + p.pass_id + " failed to board train " + id);
		return false;
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