package Model;

public class Passenger extends Thread{

	public int destination_station;
	public int pass_id;
	public Station station;

	private static int passenger_count = 0;

	boolean boarded;

	public Passenger(int ds, Station s){
		destination_station = ds;
		pass_id = passenger_count;
		station = s;
		s.addPassenger();
		passenger_count++;
		boarded = false;
	}

	public void train_check_for_destination(Train t){
		synchronized(t.passenger_lock){
			while(t.current_station != destination_station){
				try{
					t.passenger_lock.wait();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		System.out.println("Passenger " + pass_id + " has left");
		t.leave();
	}

	public Train station_wait_for_train(Station s){
		synchronized (s.train_boardable){
			while(!boarded){
				//check if theres a train in the station
				if(!s.isOccupied()){
					System.out.println("Passenger " + pass_id + " waiting for train...");
					try{
						s.train_boardable.wait();
					}catch(Exception e){
						e.printStackTrace();
					}
				}

				//train exists, check if full
				if(s.train.board(this)){
					boarded = true;
				}
			}
			return s.train;
		}
	}

	public synchronized void board_train(Train t){
		if(!boarded){
			t.board(this);
		}
	}

	public void run(){
		Train t = station_wait_for_train(station);
		station.removePassenger();
		//System.out.println("Passenger " + pass_id + " has boarded.");

		train_check_for_destination(t);
	}
}
