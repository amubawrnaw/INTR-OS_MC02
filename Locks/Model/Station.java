package Model;
import java.util.*;

public class Station{
	
	ArrayList<Passenger> passengers;

	//trains[0] = train going left
	//trains[1] = train going right
	public Train[] trains;

	public synchronized void addPassenger(Passenger p){
		passengers.add(p);
		//TODO check for train
	}

	public Station(){
		trains = new Train[2];
		trains[Train.DIRECTION_LEFT] = null;
		trains[Train.DIRECTION_RIGHT] = null;
	}

	public synchronized boolean has_train_going_left(){
		return (trains[Train.DIRECTION_LEFT]!=null);
	}

	public synchronized boolean has_train_going_right(){
		return (trains[Train.DIRECTION_RIGHT]!=null);
	}

	public synchronized void station_load_train(Train t, int count){
		//TODO this function
		trains[t.direction] = t;


	}

	public void station_wait_for_train(){
		//TODO this function
	}

	public void station_on_board(){
		//TODO this function?
	}

}