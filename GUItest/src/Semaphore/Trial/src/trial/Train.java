/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sia
 */
class Train {
    private static Train instance = new Train();
    int max_pass = 8;
    int curr_pass = 0;
    int value = 0;
    //ArrayList of all passengers static so that all trains share the list of passengers
    static ArrayList <Passenger> all_pas = new ArrayList<Passenger>();
    //ArrayList of the numbers from the ArrayList of passengers
    ArrayList <Integer> train_pas = new ArrayList<Integer>();
    boolean begin = true;
    //Semaphores for the station each array represents a station
    private Semaphore[] station = {new Semaphore(1), new Semaphore(1), new Semaphore(1),
    new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    
    public static Train getTrain(ArrayList<Passenger> pas){
        all_pas = pas;
        return instance;
    }
    
    public void action(){
        //All passengers from the arraylist being run
        for(int pps = 0 ; pps < all_pas.size() ; pps++){
            all_pas.get(pps).start();
        }
        while(this.value!=80 || this.curr_pass!=0){
            move();
            if((this.value/10 > 0) && (this.value%10 == 0)){
                System.out.println("A Train has arrived in Station" + (this.value/10));
                try {
                    //A Station is being occupied by a train
                    station[((this.value/10)-1)].acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                }
                //all passengers indicated by train_pas have their current positions incremented
                for(int temp = 0; temp<train_pas.size(); temp++){
                    all_pas.get(train_pas.get(temp)).move_pas();
                }
                //Train has left the station
                station[((this.value/10)-1)].release();
            }
        }
    }
    
    public void station_load_train(){
        synchronized(this){
        if(!this.train_pas.isEmpty()){
            //Checks all passengers in arrayList train_pas to see if they've reached their destination
            for(int i=0; i<this.train_pas.size(); i++){
                if(all_pas.get(train_pas.get(i)).origin_dest[1] == (this.value/10-1)){
                    this.train_pas.remove(i);
                    this.curr_pass--;
                }
            }
        }
        int tmp=0;
        while(Room() && isStation_NotEmpty(this.value/10-1) && tmp<all_pas.size()){
            if(all_pas.get(tmp).origin_dest[0] == (this.value/10-1) && all_pas.get(tmp).boarded==false){
                //Passenger of number tmp being boarded
                all_pas.get(tmp).boarded = true;
                this.train_pas.add(all_pas.get(tmp).pass_id);
            }
            tmp++;
        }
        }
    }
    
    //Checks Arraylist of passenger that have the same origin as int i and are not boarded
    public boolean isStation_NotEmpty(int i){
        for(int q=0; q<all_pas.size(); q++){
            if(all_pas.get(q).origin_dest[1] == i && all_pas.get(q).boarded==false){
                return true;
            }
        }
        return false;
    }
    
    
    public boolean Room(){
        return (this.curr_pass <= this.max_pass);
    }
    
    //all trains move
    public void move(){
        this.value+=2;
        if(this.value==90){
            this.value=0;
        }
    }
}
