package Model;

public class Passenger implements Runnable{

	public int destination_station;
	public int pass_id;
	public Station station;

	public Passenger(int ds, int id){
		destination_station = ds;
		pass_id = id;
	}

	

	public void run(){

	}
}
