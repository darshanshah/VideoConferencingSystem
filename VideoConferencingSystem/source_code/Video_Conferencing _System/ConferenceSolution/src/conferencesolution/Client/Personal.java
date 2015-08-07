
package conferencesolution.Client;
import com.sun.media.renderer.video.Java2DRenderer;
import CustomObjects.FileObject;
import CustomObjects.User;
import com.ibm.media.codec.video.mpeg.MpegVideo;
import com.sun.media.codec.video.mpeg.RTPDePacketizer;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.applet.Applet;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import javax.media.Buffer;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import recorder.Visual;

public class Personal extends javax.swing.JFrame implements Runnable,Serializable{
    
    
    
    String lastfilepath;
    Visual vs = null;
     ApplicationStarter as;
      boolean chaton=false,listenon=false,recording=false;
       DefaultListModel dlm;
       VoiceTransmitter svr,trasmitter;
       Inflater decompressor;
       User nus;
        VoiceReceiver svrc,receiver;
       javax.swing.JToggleButton.ToggleButtonModel tlm1;
       javax.swing.JToggleButton.ToggleButtonModel tlm2;
       MyURLPlayer my;
       MyTransmitter mytras;
       Deflater compressor;
       
    public Personal(ApplicationStarter as) {
        try
        {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        } catch (Exception e)
        {
            
        }
        lastfilepath="";
        this.as = as;
      
        dlm = new DefaultListModel();
        
        decompressor = new Inflater(true);
        compressor = new Deflater(5,true);
        
         tlm1 = new JToggleButton.ToggleButtonModel();
         tlm2 = new JToggleButton.ToggleButtonModel();
        this.getContentPane().setBackground(new Color(236,233,216));
        this.setResizable(false);
        this.setLocation(400,200);
        this.setTitle(as.username);
        this.setSize(this.MAXIMIZED_HORIZ,this.MAXIMIZED_VERT);
        initComponents();
     
        userList.setModel(dlm);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we)
            {
              logOffUser();
            }
        });
        

                
    }
    
    public void logOffUser()
    {
         User u = new User(as.username,"LogOff",new Date());
         as.onUserLogOff(u) ;
                
    }
public void run()
{
    
}


    
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        msg = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(new java.awt.Color(255, 255, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                LogOff(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(102, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton1.setForeground(new java.awt.Color(51, 0, 204));
        jButton1.setText("Message");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton2.setForeground(new java.awt.Color(0, 51, 204));
        jButton2.setText("File share");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(102, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("UserName :");

        userList.setBackground(new java.awt.Color(102, 255, 255));
        userList.setFont(new java.awt.Font("Tahoma", 1, 14));
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(userList);

        jButton3.setBackground(new java.awt.Color(153, 255, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton3.setForeground(new java.awt.Color(0, 51, 204));
        jButton3.setText("Webcam");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(102, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Connected clients to Server");

        jButton4.setBackground(new java.awt.Color(102, 255, 255));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton4.setForeground(new java.awt.Color(0, 51, 204));
        jButton4.setText("Audio/Video Record");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(102, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton5.setForeground(new java.awt.Color(0, 0, 204));
        jButton5.setText("Voice over IP");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(153, 255, 255));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton6.setForeground(new java.awt.Color(0, 51, 204));
        jButton6.setText("Local Media Player");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(102, 255, 255));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14));
        jButton7.setForeground(new java.awt.Color(0, 51, 204));
        jButton7.setText("Remote Media Player");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jMenu1.setText("Menu");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        msg.setText("Message");
        msg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgActionPerformed(evt);
            }
        });

        jMenu1.add(msg);

        jMenu1.add(jSeparator1);

        jMenuItem1.setText("Voice over IP");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem1);

        jMenu1.add(jSeparator2);

        jMenuItem2.setText("File Transfer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem2);

        jMenu1.add(jSeparator3);

        jMenuItem3.setText("Monitor Webcam");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem3);

        jMenu1.add(jSeparator4);

        jMenuItem4.setText("Recerd audio/video");
        jMenu1.add(jMenuItem4);

        jMenu1.add(jSeparator5);

        jMenuItem5.setText("Player Application");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem5);

        jMenu1.add(jSeparator6);

        jMenuItem6.setText("Transmitter");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("About");
        jMenuItem7.setText("Developers");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });

        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
// TODO add your handling code here:
        About.showMe();
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
// TODO add your handling code here:
        mytras.initializeTras();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
// TODO add your handling code here:
        mytras.initializeTras();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
// TODO add your handling code here:
        my = new MyURLPlayer();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
// TODO add your handling code here:
        as.onOpenCamera();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
// TODO add your handling code here:
        FILEController();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
// TODO add your handling code here:
        VOIPController();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void msgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgActionPerformed
// TODO add your handling code here:
          as.chat.setVisible(true);
    }//GEN-LAST:event_msgActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
// TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
// TODO add your handling code here:
        my = new MyURLPlayer();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
// TODO add your handling code here:
        /*
        AudioVideoReceiver avr = new AudioVideoReceiver("rtp://127.0.0.1:1000/audio/0");
        avr.startSending1();
         */
       VOIPController();
    }//GEN-LAST:event_jButton5ActionPerformed

    public void VOIPController()
    {
         if(jButton5.getText().equals("STOP VOIP"))
        {
            jButton5.setText("START VOIP Session");
            trasmitter.stopSpeaking();
            receiver.stopListening();
            receiver = null;
            trasmitter = null;
        }
        else
        {
        String ipadd,port,input;
        try
        {
        ipadd = JOptionPane.showInputDialog(this,"IP adress of remote terminal","127.0.0.1");
        port = JOptionPane.showInputDialog(this,"Entet port which is free","1500");
        } catch (Exception e)
        {
            return;
        }
        String rtp = "rtp://"+ipadd+":"+port+"/audio/0";
        receiver = new VoiceReceiver(rtp);
        System.out.println("Receiver Created...");
        
        trasmitter = new VoiceTransmitter(ipadd,port);
        jButton5.setText("STOP VOIP");
        JOptionPane.showMessageDialog(null,"You are connected with "+ipadd+" at port "+port);
        }
        
        
    }
    
    
    
    
    
    
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
// TODO add your handling code here:
        if(vs!=null)
        {
            vs.setVisible(true);
        }
        else
        {
            vs = new Visual();
            vs.setVisible(true);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    
    public String getRemoteIP()
    {
        return "127.0.0.1";
    }
    
    public void FILEController()
    {
         if(userList.getSelectedValue()==null)
        {
           doSelectMessage();
           return;
        }
        FileDialog fd = new FileDialog(this,"Select File to be sent ");
        
        if(lastfilepath!="")
        {
            fd.setFile(lastfilepath);
        }
        fd.setVisible(true);
       
        lastfilepath = fd.getFile();
        String path = fd.getDirectory();
        
        File f = new File(path+lastfilepath);
        int level = 5;
        try
        {
        level = Integer.parseInt(JOptionPane.showInputDialog(this,"Select value between 0-9","5").toString());
        if(level<0 ||level>9)
        {
            level = 5;
        }} catch (NumberFormatException ne)
        {
            level = 5;
        }
        compressor.setLevel(level);
        
        
        try
        {
            //RandomAccessFile raf = new RandomAccessFile(f,"r");
            
        //FileImageInputStream fis = new FileImageInputStream(f);
            //BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String fdata="";
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            
            String bu = JOptionPane.showInputDialog(this,"Enter size of Buffer","10240").toString();
           int buf=10240;
            try
            {
            buf = Integer.parseInt(bu);
            if(buf<1 || buf>65535)
                throw new NumberFormatException();
            } catch (NumberFormatException ne)
            {
                System.out.println("Invalid input given...\nNow buffer is 10240\n");
                buf = 10240;
            }
           char[] buffer = new char[buf];
           //byte[] combuf = new byte[buf];
           String total="";
           LinkedList fdt = new LinkedList();
          int pos=0;
           while(pos!=-1)
           {
               pos = br.read(buffer);
               
               if(pos==-1)
                   break;
               
               if(pos<buf)
               {
                   StringBuffer s = new StringBuffer(new String(buffer));
                   s = s.delete(pos,buf);
                   fdt.add(s.toString().toCharArray());
                   continue;
               }
               System.err.println(pos);
               /*
               compressor.setInput(buffer);
               compressor.finish();
               */
               fdt.add(buffer);
               //System.err.println(fdt.size());
               //System.err.println(new String(buffer));
           } 
           
           FileObject fo = new FileObject(as.username,((String)userList.getSelectedValue()),f,fdt);
           as.conn.write(fo);
                
            
        }catch(Exception e)
        {
            System.out.println("Jiska dar tha vo hi hua !"+e);
        }
        
    }
    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        as.onOpenCamera();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void LogOff(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_LogOff

       
    }//GEN-LAST:event_LogOff

    public void doSelectMessage()
    {
       
        if(userList.getSelectedValue()==null)
        {
           java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JOptionPane.showMessageDialog(null,"Please select user from the List");
                  }
        });
    }
    }
    
    
    private void userListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userListMouseClicked
// TODO add your handling code here:
        if(dlm.size()==0)
        {
            JOptionPane.showMessageDialog(null,"How is this possible ?\nThere must be some problem within the connection...");
            return;
        }
        jLabel1.setText(userList.getSelectedValue().toString());
    }//GEN-LAST:event_userListMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        as.chat.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        
       FILEController();
       
       // JOptionPane.showMessageDialog(null,path+lastfilepath);
        
    }//GEN-LAST:event_jButton2ActionPerformed
    
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JMenuItem msg;
    private javax.swing.JList userList;
    // End of variables declaration//GEN-END:variables
    
}
