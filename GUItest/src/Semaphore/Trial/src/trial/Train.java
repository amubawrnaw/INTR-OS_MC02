/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;

/**
 *
 * @author Sia
 */
class Train extends Thread{
    int max_pass = 8;
    int curr_pass = 0;
    int value = 0;
    int id;
    Station[] st = new Station[8];
    //ArrayList of the numbers from the ArrayList of passengers
    ArrayList <Passenger> train_pas = new ArrayList<Passenger>();
    boolean begin = true;
    //Semaphores for the station each array represents a station
    private Semaphore[] station = {new Semaphore(1), new Semaphore(1), new Semaphore(1),
    new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    
    public Train(int i, Station[] s){
        this.id = i+1;
        this.st = s;
    }
    
    @Override
    public void run(){
        //Checks whether there are still passengers on stations or if train is at the final station and without passengers
        while(this.value!=80 || this.curr_pass>0 || Station_Check()){
            move();
            if((this.value/10 > 0) && (this.value%10 == 0)){
                int q=this.value/10-1;  //index for the station that train is at
                try {
                    station[q].acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Train.class.getName()).log(Level.SEVERE, null, ex);
                }
                try{
                    ACT(q);
                    //Train has left the station
                }finally{
                    station[q].release();   //Release of train from their station
                }
            }
        }
    }
    
    public void ACT(int q){
        synchronized(this){
            System.out.println("Train " + this.id + " has arrived in Station " + (this.value/10) + " Current Passengers: " + this.curr_pass);
            //Passengers disembark first before new passengers board
            for(int temp = 0; temp<train_pas.size(); temp++){
                if((q+1) == train_pas.get(temp).origin_dest[1]){
                    //This release is for when a passenger reaches their destination and can proceed printing out that they've arrived
                    train_pas.get(temp).leave[q].release();
                    this.curr_pass--;
                }
            }
            //all passengers on this station board till max capacity
            while(Room() && !st[q].isEmpty()){
                st[q].total_pass.get(0).sem[st[q].total_pass.get(0).origin_dest[0]].release();
                train_pas.add(st[q].total_pass.get(0));
                st[q].total_pass.remove(0);
                this.curr_pass++;
            }
        }
    }
    
    //Checks whether train has room to accept passengers
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
    
    public boolean Station_Check(){
        int i=0;
        while(i<8){
            if(st[i].isEmpty()){
                return false;
            }
            i++;
        }
        return true;
    }
}
