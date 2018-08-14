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
    
    //Code is HEAVILY RELIANT on Train as only when Train reaches a Station can Passenger's code execute
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        int init=0;
        Station[] st = new Station[8];
        Train T;
        ArrayList <Train> trains = new ArrayList<Train>();
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
        for(int i=0;i<8;i++){
            st[i]=new Station();
        }
        for(int i=0;i<pssr;i++){
            Passenger tp = new Passenger(i);
            int x = tp.origin_dest[0];
            st[x].addPass(tp);
        }
        for(int i=0;i<tr;i++){
            T = new Train(i,st);
            trains.add(T);
        }
        for(int i=0;i<8;i++){
            for(int q=0;q<st[i].total_pass.size();q++){
                st[i].total_pass.get(q).start();
            }
        }
        for(int i=0;i<trains.size();i++){
            trains.get(i).start();
        }
        
    }
    
}
