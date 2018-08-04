import java.util.*;
import Model.*;
public class Driver{
	public static void main(String[] args){

		Train[] trains = new Train[16];
		Train.initStations();

		System.out.println("1-Add Train\n2-Add Passenger\n3-Exit");
		Scanner sc = new Scanner(System.in);
		String input;
		while((input = sc.nextInt()) != 3){
			//TRAIN
			if(input == 1){
				System.out.print("Enter Station (0-7): ");
				int station_num = sc.nextInt();

				

			}else{//PASSENGER

			}



		}


	}



}
