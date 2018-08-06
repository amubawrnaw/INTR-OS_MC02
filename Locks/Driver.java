import java.util.*;
import Model.*;
public class Driver{
	public static void main(String[] args){
		Train[] trains = new Train[16];
		Train.initStations();

		System.out.println("1-Add Train\n2-Add Passenger\n3-Exit");
		Scanner sc = new Scanner(System.in);
		int input;
		while((input = sc.nextInt()) != 3){
			//TRAIN
			if(input == 1){
				System.out.print("Train Capacity: ");
				int cap = sc.nextInt();
				System.out.print("How many trains: ");
				int tcount = sc.nextInt();
				while(tcount-->0){
					new Train(cap,1).start();
					try{
						Thread.sleep(100);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}else{//PASSENGER
				System.out.print("Enter start station (0-7): ");
				int start = sc.nextInt();
				System.out.print("Enter destination station (0-7): ");
				int end = sc.nextInt();
				System.out.print("How many passengers: ");
				int cnt = sc.nextInt();

				while(cnt-->0){
					new Passenger(end,Train.stations[start]).start();
				}
			}
			try{
				Thread.sleep(100);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println("1-Add Train\n2-Add Passenger\n3-Exit");
		}
	}
}
