package Model;

import java.util.*;

public class Train extends Thread{
	public static String DIRECTION_LEFT = 0;
	public static String DIRECTION_RIGHT = 1;

	public int max_seat_count;
	private int occupied_seats;

	private int direction;



	public static Station[] stations;

	

	public Train(int seat_count, int direction){
		


	}

	@Override
	public void run(){
		
	}

	public static void initStations(){
		stations = new Station[8];
		for(int i = 0 ; i < 8 ; i++){
			Station temp = new Station();

			stations[i] = temp;
		}
	}
}