import java.util.*;
import Model.*;
public class Driver{
	public static void main(String[] args){
		int t_count = 0;
		Train[] trains = new Train[16];
		Train.initStations();

		System.out.println("1-Add Train\n2-Add Passenger\n3-Exit");
		Scanner sc = new Scanner(System.in);
		int input;
		while((input = sc.nextInt()) != 3){
			//TRAIN
			if(input == 1){
				if(t_count>15){
					System.out.println("Too many trains");
				}else{
					System.out.print("Train Capacity: ");
					int cap = sc.nextInt();

					Train t = new Train(cap,1, t_count);
					t_count++;
					t.start();
				}
			}else{//PASSENGER
				System.out.print("Enter start station (0-7): ");
				int start = sc.nextInt();
				System.out.print("Enter destination station (0-7): ");
				int end = sc.nextInt();

				Passenger p = new Passenger(end,Train.stations[start]);
				p.start();
			}
			System.out.println("1-Add Train\n2-Add Passenger\n3-Exit");
		}
	}
}
