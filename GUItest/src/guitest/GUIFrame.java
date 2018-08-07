/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitest;

import AppPackage.AnimationClass;
import AppPackage.AnimationClass.*;
import Locks.Model.Passenger;
import Locks.Model.Train;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JLabel;
/**
 *
 * @author N7
 */
public class GUIFrame extends javax.swing.JFrame {

    /**
     * Creates new form GUIFrame
     */
    public GUIFrame() {
        random_passenger_destination = new Random();
        selected_station_for_passenger = 0;
        
        initComponents();
        customInit();
    }
    
    
    JLabel[] trains;
    AnimationClass[] animators;
    int[] top_stations;
    int[] bottom_stations;
    HashMap<Integer, Integer> train_indexes;
    private void customInit(){
        Train.initStations();
        //link the stations to the GUI passenger display
        Train.stations[0].gui_count = station_1_PCount;
        Train.stations[1].gui_count = station_2_PCount;
        Train.stations[2].gui_count = station_3_PCount;
        Train.stations[3].gui_count = station_4_PCount;
        Train.stations[4].gui_count = station_5_PCount;
        Train.stations[5].gui_count = station_6_PCount;
        Train.stations[6].gui_count = station_7_PCount;
        Train.stations[7].gui_count = station_8_PCount;
        //X coordinates for Stations / Tracks for Stations 1-4
        top_stations = new int[]{-120,50,220,400,590,770,960,1130,1300};
        //X coordinates for Stations / Tracks for Stations 5-8
        bottom_stations = new int[]{60,230,400,590,770,950,1130,1300};
        //this will store the indexes of the trains in the station X coordinate array
        
        
    }
    
    public void putTrains(){
        train_indexes = new HashMap<>();
        
        //instantiate the train list
        trains = new JLabel[16];
        //instantiate the animation class, 1 AC : 1 Train
        animators = new AnimationClass[16];
        
        //instantiate stuff
        for(int i = 0 ; i < 16 ; i++){
            //initialize index of each train to 0
            train_indexes.put(i,0);
            
            //create a train gui
            JLabel temp = new JLabel();
            temp.setFont(new java.awt.Font("BigNoodleTitling", 0, 18));
            temp.setForeground(new java.awt.Color(255, 255, 255));
            temp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/train_icon.png")));
            temp.setText("x0");
            temp.setAutoscrolls(true);
            getContentPane().add(temp);
            temp.setBounds(-120, 150, 190, 70);
            //initially invisible
            temp.setVisible(false);
            
            //add new train gui to list
            trains[i] = temp;
            animators[i] = new AnimationClass();
        }
    }
    
    public void setPassenger(int train_id, int pass_count){
        trains[train_id].setText("x" + pass_count);
    }
    public void moveToNextStation(int train_id){
        //check if train is at the rightmost edge of the GUI
        if(train_indexes.get(train_id)==7){
            //check if train is on Station 4
            if(trains[train_id].getBounds().y == 150){
                //move to station 5
                trains[train_id].setBounds(-120, 360, 190,70);
                animators[train_id].jLabelXRight(-120, 60, 10, 10, trains[train_id]);
                train_indexes.put(train_id,0);
            }else{
                //move to station 1
                trains[train_id].setBounds(-120, 150, 190,70);
                train_indexes.put(train_id,0);
            }
        }else{
            //move normally
            if(trains[train_id].getBounds().y == 150){
                animators[train_id].jLabelXRight(top_stations[train_indexes.get(train_id)],top_stations[train_indexes.get(train_id)+1] , 10, 10, trains[train_id]);
                trains[train_id].setBounds(top_stations[train_indexes.get(train_id)], 150, 190,70);
                train_indexes.put(train_id,train_indexes.get(train_id)+1);
            }else{
                animators[train_id].jLabelXRight(bottom_stations[train_indexes.get(train_id)],bottom_stations[train_indexes.get(train_id)+1] , 10, 10, trains[train_id]);
                trains[train_id].setBounds(bottom_stations[train_indexes.get(train_id)], 360, 190,70);
                train_indexes.put(train_id,train_indexes.get(train_id)+1);
            }
        }
    }
    
    public void leaveSimulation(int train_id){
        animators[train_id].jLabelXRight(1130, 1300, 10, 10, trains[train_id]);
        train_indexes.put(train_id, 0);
        trains[train_id].setVisible(false);
        trains[train_id].setBounds(-120, 150, 190, 70);
    }
    
    public void activateTrain(int train_id){
        trains[train_id].setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        deployTrainButton = new javax.swing.JButton();
        PassengerSpinner = new javax.swing.JSpinner();
        trainCapacitySpinner = new javax.swing.JSpinner();
        Station = new javax.swing.JLabel();
        TrainCapacity = new javax.swing.JLabel();
        station_1_PCount = new javax.swing.JLabel();
        station_2_PCount = new javax.swing.JLabel();
        station_3_PCount = new javax.swing.JLabel();
        station_4_PCount = new javax.swing.JLabel();
        station_5_PCount = new javax.swing.JLabel();
        station_6_PCount = new javax.swing.JLabel();
        station_7_PCount = new javax.swing.JLabel();
        station_8_PCount = new javax.swing.JLabel();
        passenger_station_1 = new javax.swing.JToggleButton();
        passenger_station_2 = new javax.swing.JToggleButton();
        passenger_station_3 = new javax.swing.JToggleButton();
        passenger_station_4 = new javax.swing.JToggleButton();
        passenger_station_5 = new javax.swing.JToggleButton();
        passenger_station_6 = new javax.swing.JToggleButton();
        passenger_station_7 = new javax.swing.JToggleButton();
        passenger_station_8 = new javax.swing.JToggleButton();
        AddPassengers = new javax.swing.JButton();
        Background = new javax.swing.JLabel();

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("BigNoodleTitling", 0, 24)); // NOI18N
        jTextField1.setText("1");
        jTextField1.setOpaque(false);
        jTextField1.setRequestFocusEnabled(false);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1177, 562));
        setResizable(false);
        getContentPane().setLayout(null);

        deployTrainButton.setBackground(new java.awt.Color(255, 255, 255));
        deployTrainButton.setFont(new java.awt.Font("BigNoodleTitling", 0, 36)); // NOI18N
        deployTrainButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/deploydefault.png"))); // NOI18N
        deployTrainButton.setBorder(null);
        deployTrainButton.setBorderPainted(false);
        deployTrainButton.setContentAreaFilled(false);
        deployTrainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deployTrainButtonActionPerformed(evt);
            }
        });
        getContentPane().add(deployTrainButton);
        deployTrainButton.setBounds(1010, 440, 210, 90);

        PassengerSpinner.setFont(new java.awt.Font("BigNoodleTitling", 0, 24)); // NOI18N
        getContentPane().add(PassengerSpinner);
        PassengerSpinner.setBounds(510, 500, 70, 50);

        trainCapacitySpinner.setFont(new java.awt.Font("BigNoodleTitling", 0, 24)); // NOI18N
        getContentPane().add(trainCapacitySpinner);
        trainCapacitySpinner.setBounds(920, 460, 70, 50);

        Station.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/capacity.png"))); // NOI18N
        getContentPane().add(Station);
        Station.setBounds(700, 460, 240, 60);

        TrainCapacity.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/station.png"))); // NOI18N
        getContentPane().add(TrainCapacity);
        TrainCapacity.setBounds(60, 426, 180, 74);

        station_1_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_1_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_1_PCount.setText("x0");
        getContentPane().add(station_1_PCount);
        station_1_PCount.setBounds(120, 80, 40, 30);

        station_2_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_2_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_2_PCount.setText("x0");
        getContentPane().add(station_2_PCount);
        station_2_PCount.setBounds(470, 80, 40, 30);

        station_3_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_3_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_3_PCount.setText("x0");
        getContentPane().add(station_3_PCount);
        station_3_PCount.setBounds(840, 80, 40, 30);

        station_4_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_4_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_4_PCount.setText("x0");
        getContentPane().add(station_4_PCount);
        station_4_PCount.setBounds(1200, 80, 30, 30);

        station_5_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_5_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_5_PCount.setText("x0");
        getContentPane().add(station_5_PCount);
        station_5_PCount.setBounds(130, 290, 40, 30);

        station_6_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_6_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_6_PCount.setText("x0");
        getContentPane().add(station_6_PCount);
        station_6_PCount.setBounds(470, 290, 50, 30);

        station_7_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_7_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_7_PCount.setText("x0");
        getContentPane().add(station_7_PCount);
        station_7_PCount.setBounds(840, 290, 40, 30);

        station_8_PCount.setFont(new java.awt.Font("BigNoodleTitling", 0, 18)); // NOI18N
        station_8_PCount.setForeground(new java.awt.Color(255, 255, 255));
        station_8_PCount.setText("x0");
        getContentPane().add(station_8_PCount);
        station_8_PCount.setBounds(1200, 290, 40, 30);

        passenger_station_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num1Stationdef.png"))); // NOI18N
        passenger_station_1.setBorderPainted(false);
        passenger_station_1.setContentAreaFilled(false);
        passenger_station_1.setFocusPainted(false);
        passenger_station_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_1ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_1);
        passenger_station_1.setBounds(240, 420, 50, 80);

        passenger_station_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num2Stationdef.png"))); // NOI18N
        passenger_station_2.setBorderPainted(false);
        passenger_station_2.setContentAreaFilled(false);
        passenger_station_2.setFocusPainted(false);
        passenger_station_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_2ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_2);
        passenger_station_2.setBounds(290, 420, 50, 80);

        passenger_station_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num3Stationdef.png"))); // NOI18N
        passenger_station_3.setBorderPainted(false);
        passenger_station_3.setContentAreaFilled(false);
        passenger_station_3.setFocusPainted(false);
        passenger_station_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_3ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_3);
        passenger_station_3.setBounds(340, 420, 50, 80);

        passenger_station_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num4Stationdef.png"))); // NOI18N
        passenger_station_4.setBorderPainted(false);
        passenger_station_4.setContentAreaFilled(false);
        passenger_station_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_4ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_4);
        passenger_station_4.setBounds(390, 410, 50, 100);

        passenger_station_5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num5Stationdef.png"))); // NOI18N
        passenger_station_5.setBorderPainted(false);
        passenger_station_5.setContentAreaFilled(false);
        passenger_station_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_5ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_5);
        passenger_station_5.setBounds(440, 420, 50, 80);

        passenger_station_6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num6Stationdef.png"))); // NOI18N
        passenger_station_6.setBorderPainted(false);
        passenger_station_6.setContentAreaFilled(false);
        passenger_station_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_6ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_6);
        passenger_station_6.setBounds(490, 420, 50, 80);

        passenger_station_7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num7Stationdef.png"))); // NOI18N
        passenger_station_7.setBorderPainted(false);
        passenger_station_7.setContentAreaFilled(false);
        passenger_station_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_7ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_7);
        passenger_station_7.setBounds(540, 420, 50, 80);

        passenger_station_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/Num8Stationdef.png"))); // NOI18N
        passenger_station_8.setBorderPainted(false);
        passenger_station_8.setContentAreaFilled(false);
        passenger_station_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passenger_station_8ActionPerformed(evt);
            }
        });
        getContentPane().add(passenger_station_8);
        passenger_station_8.setBounds(590, 420, 50, 80);

        AddPassengers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/addpass3.png"))); // NOI18N
        AddPassengers.setBorderPainted(false);
        AddPassengers.setContentAreaFilled(false);
        AddPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPassengersActionPerformed(evt);
            }
        });
        getContentPane().add(AddPassengers);
        AddPassengers.setBounds(260, 480, 257, 90);

        Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guitest/GUI Images/FINALBG2.png"))); // NOI18N
        putTrains();
        getContentPane().add(Background);
        Background.setBounds(0, 0, 1330, 560);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int selected_station_for_passenger;
    private void deployTrainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deployTrainButtonActionPerformed
       int train_capacity = (Integer) trainCapacitySpinner.getValue();
       
       new Train(train_capacity, 1, this).start();
       
       
    }//GEN-LAST:event_deployTrainButtonActionPerformed

    private void passenger_station_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_1ActionPerformed
        selected_station_for_passenger = 0;
    }//GEN-LAST:event_passenger_station_1ActionPerformed

    private void passenger_station_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_4ActionPerformed
        selected_station_for_passenger = 3;
    }//GEN-LAST:event_passenger_station_4ActionPerformed

    private void passenger_station_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_5ActionPerformed
        selected_station_for_passenger = 4;
    }//GEN-LAST:event_passenger_station_5ActionPerformed

    private void passenger_station_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_8ActionPerformed
        selected_station_for_passenger = 7;
    }//GEN-LAST:event_passenger_station_8ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void passenger_station_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_6ActionPerformed
        selected_station_for_passenger = 5;
    }//GEN-LAST:event_passenger_station_6ActionPerformed

    private void passenger_station_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_2ActionPerformed
        selected_station_for_passenger = 1;
    }//GEN-LAST:event_passenger_station_2ActionPerformed

    private void passenger_station_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_3ActionPerformed
        selected_station_for_passenger = 2;
    }//GEN-LAST:event_passenger_station_3ActionPerformed

    private void passenger_station_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passenger_station_7ActionPerformed
        selected_station_for_passenger = 6;
    }//GEN-LAST:event_passenger_station_7ActionPerformed
    Random random_passenger_destination;
    private void AddPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPassengersActionPerformed
        //creates a random int from 0-7
        int end = random_passenger_destination.nextInt(8);
        int cnt = (Integer )PassengerSpinner.getValue();
        if(cnt>0){
            for(int i = 0 ; i < cnt ; i++){
                end = random_passenger_destination.nextInt(8);
                while(end==selected_station_for_passenger){
                    //creates a random int from 0-7
                    end = random_passenger_destination.nextInt(8);
                }
                new Passenger(end, Train.stations[selected_station_for_passenger]).start();
            }
            
        }else{
            System.out.println("Invalid Passenger Count!");
        }
        
    }//GEN-LAST:event_AddPassengersActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPassengers;
    private javax.swing.JLabel Background;
    private javax.swing.JSpinner PassengerSpinner;
    private javax.swing.JLabel Station;
    private javax.swing.JLabel TrainCapacity;
    private javax.swing.JButton deployTrainButton;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton passenger_station_1;
    private javax.swing.JToggleButton passenger_station_2;
    private javax.swing.JToggleButton passenger_station_3;
    private javax.swing.JToggleButton passenger_station_4;
    private javax.swing.JToggleButton passenger_station_5;
    private javax.swing.JToggleButton passenger_station_6;
    private javax.swing.JToggleButton passenger_station_7;
    private javax.swing.JToggleButton passenger_station_8;
    private javax.swing.JLabel station_1_PCount;
    private javax.swing.JLabel station_2_PCount;
    private javax.swing.JLabel station_3_PCount;
    private javax.swing.JLabel station_4_PCount;
    private javax.swing.JLabel station_5_PCount;
    private javax.swing.JLabel station_6_PCount;
    private javax.swing.JLabel station_7_PCount;
    private javax.swing.JLabel station_8_PCount;
    private javax.swing.JSpinner trainCapacitySpinner;
    // End of variables declaration//GEN-END:variables
}
