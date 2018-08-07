package Locks.Model;
import java.util.*;
import javax.swing.JLabel;

public class Station{
	
	public int passengers; 
        public JLabel gui_count;
	//trains[0] = train going left
	//trains[1] = train going right
	public Train train;
	public Object train_lock;
	public Object train_boardable;
	
	public synchronized void addPassenger(){
		passengers++;
                gui_count.setText("x" + passengers);
	}
	public synchronized void removePassenger(){
		passengers--;
                gui_count.setText("x" + passengers);
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

	public boolean station_load_train(Train t){
		
		synchronized(train_boardable){
			if(train == null){
				train = t;
				train_boardable.notifyAll();
				return true;
			}
			return false;
		}
	}

	public void station_leave_train(){
		synchronized(train_lock){
			train = null;
			train_lock.notifyAll();
		}
	}
} 