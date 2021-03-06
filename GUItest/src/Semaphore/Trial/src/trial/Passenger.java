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
class Passenger extends Thread{
    //Array 0 is origin while Array 1 is destination
    int[] origin_dest = new int[2];
    int current_val;
    int pass_id;
    Train tr;
    //All Semaphores are set to 0 so they have to wait for a release when train arrives at a station
    Semaphore[] sem = {new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true)
    , new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true)};
    //All Semaphores are set to 0 so that code is only executed if train has arrived at a passenger's destination
    Semaphore[] leave = {new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true)
    , new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true), new Semaphore(0, true)};

    Passenger(int i) {
        //origin and destination randomly chosen
        this.origin_dest = new Random().ints(0, 8).distinct().limit(2).toArray();
        this.pass_id = i+1;
        this.current_val = this.origin_dest[0];
    }
    
    @Override
    public void run(){
        //Message of witing immediately called
        msg();
        try {
            sem[origin_dest[0]].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized(this){
            boarded();
        }
        try {
            leave[origin_dest[0]].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (this){
            arrived();
        }
    }
    
    public void assign_Train(Train T){
        this.tr = T;
    }
    
    
    public void act(){
        
    }
    
    public void move_pas(){
        current_val++;
        if(current_val==9){
            current_val=1;
        }
    }
    
    public void move(){
        this.current_val++;
        if(this.current_val==8){
            this.current_val=0;
        }
    }
    
    public void slow(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Passenger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void msg(){
        System.out.println("Passenger " + this.pass_id + " is now waiting at station " + (this.origin_dest[0]+1));
        System.out.println("Passenger " + this.pass_id + " destination at station " + (this.origin_dest[1]+1));
    }
    
    public void boarded(){
        System.out.println("Passenger " + this.pass_id + " has boarded.");
    }
    
    public void arrived(){
        System.out.println("Passenger " + this.pass_id + " has arrived at their destination.");
    }
    
}
