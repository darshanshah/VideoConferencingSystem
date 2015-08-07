package conferencesolution.Client;



import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.FileDialog;
import javax.media.*;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class MyURLPlayer extends javax.swing.JFrame  {
    
   
    
    Player pl,def;
    MediaLocator ml,dml;
    RemoteURLPlayer s,rup;
    URL u;
    Thread th;
    javax.media.protocol.DataSource ds;
private MediaLocator lastURL;    
    
    public MyURLPlayer() {
         try
    {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
         this.getContentPane().setBackground(new Color(236,233,216));
    } catch(Exception e)
    {
        
    }
        initComponents();
        pl = null;
        ml=null;
        this.setVisible(true);
    }
    
  
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Player application");
        setResizable(false);
        jButton1.setBackground(new java.awt.Color(51, 255, 255));
        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setBackground(new java.awt.Color(51, 255, 255));
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jTextField1.setText("vfw://0");
        jTextField1.setSelectedTextColor(new java.awt.Color(255, 255, 102));
        jTextField1.setSelectionColor(new java.awt.Color(255, 153, 153));

        jButton2.setBackground(new java.awt.Color(51, 255, 255));
        jButton2.setText("Stop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 255, 255));
        jButton3.setText("Exit");
        jButton3.setToolTipText("Do I need to Explain It?");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(0, 102, 153));
        jLabel1.setText("for chatting dsound://1 or 0");

        jButton4.setBackground(new java.awt.Color(51, 255, 255));
        jButton4.setText("Webcam");
        jButton4.setToolTipText("Just gives you a RTP URL,hence u need to modify it.");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 255, 255));
        jButton5.setText("Audio");
        jButton5.setToolTipText("Audio receiver with buffering");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(51, 255, 255));
        jButton6.setText("Clone URL");
        jButton6.setToolTipText("Create Multiple Datasources");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(0, 255, 255));
        jButton7.setFont(new java.awt.Font("Georgia", 0, 18));
        jButton7.setText("Browse Media");
        jButton7.setToolTipText("Play Stored Media");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14));
        jLabel2.setForeground(new java.awt.Color(0, 51, 204));
        jLabel2.setText("Experience the Thrilling Sound of JAVA");

        jButton8.setBackground(new java.awt.Color(51, 51, 51));
        jButton8.setText("Send to");
        jButton8.setEnabled(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(51, 255, 255));
        jButton9.setText("Default Audio Player");
        jButton9.setToolTipText("We used it for testing, u might find it interesting");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel3.setText("Enter URL and Press Start");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton8)
                    .addComponent(jButton5)
                    .addComponent(jButton3)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
// TODO add your handling code here:
         String s;
         th = null;
         
        try
        {
        s =JOptionPane.showInputDialog(this,"Enter address URL",jTextField1.getText());
        } catch (Exception e)
        {
           return;
        }
         dml = new MediaLocator(s);
            Runnable t= new java.lang.Runnable() {
                 public void run() {
                     try
         {
         
                     rup = new RemoteURLPlayer(Manager.createRealizedPlayer(dml));
                     rup.start(null);
                     } catch (Exception w)
         {
             System.out.println(w);
             return;
         }    
     
                 }
             };
             
         th = new Thread(t,"Parellal Player");
         th.start();
         
         
         
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
// TODO add your handling code here:`
        try
        {
        String ipadd = InetAddress.getLocalHost().getHostAddress();
        String ip = JOptionPane.showInputDialog(this,"Enter IP Address ",ipadd);
        String po = JOptionPane.showInputDialog(this,"Enter port","1000");
        int port = Integer.parseInt(po);
        String f = JOptionPane.showInputDialog(this,"Enter URL","rtp://"+ipadd+":"+po+"/video").toString();
        createDataSource(f);
        }
        
        catch (Exception e)
        {
            
        }
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
        String url="";
        try
        {
        jTextField1.setText("rtp://"+InetAddress.getLocalHost().getHostAddress()+":1500/audio/0");
        
        url = JOptionPane.showInputDialog(this,"You are going to create URL for",jTextField1.getText());
        } catch(Exception e)
        {
           JOptionPane.showMessageDialog(null,"OK, retry...");
           return;
        }
        AudioBufferControl abc = new AudioBufferControl(url);
        //abc.startSendingTo(jTextField1.getText());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
     
        try {
        jTextField1.setText("rtp://"+InetAddress.getLocalHost().getHostAddress()+":2000/video");    
   
        } catch (Exception w)
        {}
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO add your handling code here:
        FileDialog fd = new FileDialog(this);
        fd.setVisible(true);
        File f = new File(fd.getDirectory()+fd.getFile());
        // System.out.println(f.getAbsolutePath());
        try
        {
       
        String tmp= f.getAbsolutePath();
     
        URL u = new URL("file:/"+tmp);
        jTextField1.setText(u.toString());
      
        createDataSource(u.toString());
        } catch (MalformedURLException ml)
        {
            System.out.println(f.getAbsolutePath());
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        if(s!=null && s.player.Started==1)
        s.player.stop();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// TODO add your handling code here:
        if(s!=null && s.player.Started==1)
        {
        s.player.stop();
        s.removeListener();
        s.close();
        }
        this.dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    public void createDataSource(String s)
    {
        try
        {
        ml = new MediaLocator(s);
        if(ml==null)
        {
            System.out.println("MediaLocator can't be null");
        }
        Clone c = new Clone();
        String users =null;
        try
        {
                users= JOptionPane.showInputDialog("Enter No. of copies","1" ).toString();
        } catch (NullPointerException b)
        {
            users = "1";
        }
        if(users.equals("")||users==null||Integer.parseInt(users)<1)
            return;
        c.init(ml,Integer.parseInt(users));
        } catch (NullPointerException w)
        {
            System.out.println(w);
        }
    }
    
    
    
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO add your handling code here:
        System.out.println(jTextField1.getText());
        createDataSource(jTextField1.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        try
        {
            lastURL = ml;
        ml = new MediaLocator(jTextField1.getText());
        
        startPlayer();
        
        
        } catch (Exception e)
        {
            System.out.println(e);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    public void startPlayer()
    {
        try
        {
            if(ml.equals(lastURL))
            {
                
                s.start(lastURL);
            }
        pl = Manager.createRealizedPlayer(ml);
        s  = new RemoteURLPlayer(pl);
        s.start(ml);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyURLPlayer().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
