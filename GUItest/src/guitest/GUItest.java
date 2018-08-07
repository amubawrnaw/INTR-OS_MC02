/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

/**
 *
 * @author N7
 */


import javax.swing.*;
import java.util.Scanner;
import AppPackage.AnimationClass.*;


public class GUItest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         
        GUIFrame theGUI = new GUIFrame();
        Scanner reader = new Scanner(System.in);

theGUI.setSize(1325, 585);
theGUI.setDefaultCloseOperation(GUIFrame.EXIT_ON_CLOSE);
theGUI.setTitle("GUI Window");
theGUI.setVisible(true);
        
    }
    
}
