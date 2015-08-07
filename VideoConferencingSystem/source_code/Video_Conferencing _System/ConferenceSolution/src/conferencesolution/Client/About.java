/*
 * About.java
 *
 * Created on June 24, 2008, 11:22 PM
 */

package conferencesolution.Client;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import javax.swing.UIManager;


public class About extends javax.swing.JFrame implements Runnable{
    
    
    Color c1,c2,c3,c4;
    Random r1,r2,r3;
    int r,g,b;
    Thread k;
    /** Creates new form About */
    public About() {
        
        try
        {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e)
        {
            System.out.println(e);
        }
        initComponents();

        k = new Thread(this);
       
        r=0;g=100;b=200;
        
        r3 = new Random();
         k.start();
    }
    
    public void run()
    {
        while(true)
        {
           
        jLabel1.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel1.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel2.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel2.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel3.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel3.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel6.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel6.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel7.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel7.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        
          jLabel4.setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        jLabel4.setForeground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
        getContentPane().setBackground(new Color(r3.nextInt(255),r3.nextInt(255),r3.nextInt(255)));
      
        
        try
        {
        Thread.currentThread().sleep(550);
        } catch (Exception e)
        {
            
        }
        }
        
    }
    
    
    
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Developed By K®UNAL");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        jLabel1.setText("Darshan Shah");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setText("07CE127");

        jLabel3.setFont(new java.awt.Font("Candara", 1, 36)); // NOI18N
        jLabel3.setText("BIT");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Dedicated to Our Parents");

        jLabel5.setFont(new java.awt.Font("Century Schoolbook", 1, 24)); // NOI18N
        jLabel5.setText("Created By");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setText("Dipesh Shah");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel7.setText("07CE98");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About().setVisible(true);
            }
        });
    }
    public static void showMe()
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
    
}
