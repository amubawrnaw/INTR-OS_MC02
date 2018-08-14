/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial;

import java.util.*;
/**
 *
 * @author Sia
 */

//Station are all organized by their passengers origin.
public class Station {
    ArrayList <Passenger> total_pass = new ArrayList<Passenger>();
    
    public void addPass(Passenger p){
        this.total_pass.add(p);
    }
    
    //Checks whether a station has passengers waiting to board
    public boolean isEmpty(){
        return total_pass.isEmpty();
    }
}
