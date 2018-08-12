/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import java.util.*;
import java.util.concurrent.*;

/**
 *
 * @author Sia
 */
public class Trial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int init=0;
        ArrayList <Passenger> Stations = new ArrayList<Passenger>();
        ArrayList <Train> trains = new ArrayList<Train>();
        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.print("Indicate the number of passengers(limit 50): ");
        Scanner sc = new Scanner(System.in);
        int pssr, tr;
        do{
            pssr = sc.nextInt();
            sc.nextLine();
            if(pssr<1 || pssr>50){
                System.out.println("Error. Please enter a number between 1-50");
            }
        }while(pssr<1 || pssr>50);
        System.out.print("Indicate the number of trains(limit 16): ");
        do{
            tr = sc.nextInt();
            sc.nextLine();
            if(tr<1 || tr>16){
                System.out.println("Error. Please enter a number between 1-16");
            }
        }while(tr<1 || tr>16);
        //ArrayList of passengers being filled up
        for(int i=0;i<pssr;i++){
            Passenger tp = new Passenger(i);
            Stations.add(tp);
        }
        for(int i=0;i<tr;i++){
            exec.submit(new Runnable(){
                public void run(){
                    //ArrayList of passengers being passed onto the trains and thread being executed 
                    Train.getTrain(Stations).action();
                }
            });
        }
        
        exec.shutdown();
        
        exec.awaitTermination(1, TimeUnit.DAYS);
    }
    
}
