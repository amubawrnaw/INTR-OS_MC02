package Model;
import java.util.*;

public class Station{
	
	public int passengers; 

	//trains[0] = train going left
	//trains[1] = train going right
	public Train train;
	public Object train_lock;
	public Object train_boardable;
	public synchronized void addPassenger(){
		passengers++;
	}
	public synchronized void removePassenger(){
		passengers--;
	}
	public Station(){
		train = null;
		passengers = 0;
		train_lock = new Object();
		train_boardable = new Object();
	}

	public boolean hasPassengers(){
		return (passengers>0);
	}

	public boolean isOccupied(){
		return (train!=null);
	}

	public synchronized boolean station_load_train(Train t){
		if(train == null){
			train = t;
			train_boardable.notifyAll();
			return true;
		}
		return false;
	}

	public synchronized void station_leave_train(){
		train = null;
		train_lock.notifyAll();
	}

	public void station_on_board(){
		//TODO this function?
	}

}