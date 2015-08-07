/*
 * Visual.java
 *
 * Created on June 19, 2008, 3:52 PM
 */

package recorder;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.StringTokenizer;
import javax.media.DataSink;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Processor;
import javax.media.ProcessorModel;
import javax.media.control.MonitorControl;
import javax.media.format.AudioFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;
import javax.media.protocol.FileTypeDescriptor;
import javax.swing.UIManager;

/**
 *
 * @author  Krunal
 */
public class Visual extends javax.swing.JFrame {
    
    /** Creates new form Visual */
 Processor processor = null;
    DataSink datasink = null;
    Component monitor = null;
    DataSource datasource = null;
    String outputType = "video.x_msvideo";
    
    boolean paused = false;
    public Visual() {
          try
    {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
         this.getContentPane().setBackground(new Color(236,233,216));
    } catch(Exception e)
    {
        
}
        
        initComponents();
        startTheStuff();
    }
    
    public void startTheStuff()
    {
        if (processor != null) {
	    processor.stop();
	    processor.close();
	}
	// Remove the previous monitor
	if (monitor != null) {
	      monitor = null;
    }
        AudioFormat af = null;
	VideoFormat vf = null;
        if(audioenabled.isSelected())
        {
            int samplingRate = Integer.parseInt(frequency.getSelectedItem().toString());
            int samplingSize = lowquality.isSelected() ? 8:16;
	    int channels = monoaudio.isSelected()? 1:2;
             af = new AudioFormat(AudioFormat.LINEAR, samplingRate, samplingSize,
				 channels);
        }
        
        if (videoenabled.isSelected()) {
	    String encoding = visualmode.getSelectedItem().toString();
	    String strSize = resolution.getSelectedItem().toString();
	    StringTokenizer st = new StringTokenizer(strSize, "x");
	    int sizeX = Integer.parseInt(st.nextToken());
	    int sizeY = Integer.parseInt(st.nextToken());
	    Dimension size = new Dimension(sizeX, sizeY);

	    vf = new VideoFormat(encoding, size, Format.NOT_SPECIFIED, null, 15f);
	}
        datasource = CaptureUtil.getCaptureDS(vf, af);
	
	if (datasource != null) {
	    // Set the preferred content type for the Processor's output
	    outputType = "video.quicktime";
            
            
	    if (formatbox.getSelectedItem().equals("AVI"))
		outputType = "video.x_msvideo";
	    FileTypeDescriptor ftd = new FileTypeDescriptor(outputType);
	    Format [] formats = null;
            

	    if (af != null && vf != null) {
		formats = new Format[] { new AudioFormat(null),
					 new VideoFormat(null) };
	    }

	    if (af == null)
		formats = new Format[] {new VideoFormat(null)};

	    ProcessorModel pm = new ProcessorModel(datasource, formats, ftd);
	    try {
		processor = Manager.createRealizedProcessor(pm);
	    } catch (Exception me) {
		System.err.println(me);
		// Make sure the capture devices are released
		datasource.disconnect();
		return;
	    }

	    // Get the monitor control:
	    // Since there are more than one MonitorControl objects
	    // exported by the DataSource, we get the specific one
	    // that is also the MonitorStream object.
	    MonitorControl mc = (MonitorControl)datasource.getControl("MonitorStream");
	    if (mc != null) {
		monitor = mc.getControlComponent();
		monitor.setVisible(true);
		// Make sure the monitor is enabled
		mc.setEnabled(true);
		pack();
	    }
	}
    }
    
    
    
    
    /*
     private void startMonitoring() {
	// Close the previous processor, which in turn closes the capture device
	if (processor != null) {
	    processor.stop();
	    processor.close();
	}
	// Remove the previous monitor
	if (monitor != null) {
	    panel1.remove(monitor);
	    monitor = null;
	}
        

	AudioFormat af = null;
	VideoFormat vf = null;

	if (checkAudio.getState()) {
	    // Need audio
	    int samplingRate = Integer.parseInt(comboSampling.getSelectedItem());
	    int samplingSize = radio8bit.getState() ? 8:16;
	    int channels = radioMono.getState()? 1:2;
	    af = new AudioFormat(AudioFormat.LINEAR, samplingRate, samplingSize,
				 channels);
	}

	if (checkVideo.getState()) {
	    String encoding = comboEncoding.getSelectedItem();
	    String strSize = comboSize.getSelectedItem();
	    StringTokenizer st = new StringTokenizer(strSize, "x");
	    int sizeX = Integer.parseInt(st.nextToken());
	    int sizeY = Integer.parseInt(st.nextToken());
	    Dimension size = new Dimension(sizeX, sizeY);

	    vf = new VideoFormat(encoding, size, Format.NOT_SPECIFIED,
				 null, 15f);
	}

	// Use CaptureUtil to create a monitored capture datasource
	datasource = CaptureUtil.getCaptureDS(vf, af);
	
	if (datasource != null) {
	    // Set the preferred content type for the Processor's output
	    outputType = "video.quicktime";
            
            
	    if (comboFileType.getSelectedItem().equals("AVI"))
		outputType = "video.x_msvideo";
	    FileTypeDescriptor ftd = new FileTypeDescriptor(outputType);
	    Format [] formats = null;
            

	    if (af != null && vf != null) {
		formats = new Format[] { new AudioFormat(null),
					 new VideoFormat(null) };
	    }

	    if (af == null)
		formats = new Format[] {new VideoFormat(null)};

	    ProcessorModel pm = new ProcessorModel(datasource, formats, ftd);
	    try {
		processor = Manager.createRealizedProcessor(pm);
	    } catch (Exception me) {
		System.err.println(me);
		// Make sure the capture devices are released
		datasource.disconnect();
		return;
	    }

	    // Get the monitor control:
	    // Since there are more than one MonitorControl objects
	    // exported by the DataSource, we get the specific one
	    // that is also the MonitorStream object.
	    MonitorControl mc = (MonitorControl)datasource.getControl("MonitorStream");
	    if (mc != null) {
		monitor = mc.getControlComponent();
		panel1.add(monitor);
		// Make sure the monitor is enabled
		mc.setEnabled(true);
		pack();
	    }
	}
    }
    */
    
    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        formatbox = new javax.swing.JComboBox();
        videoenabled = new javax.swing.JCheckBox();
        visualmode = new javax.swing.JComboBox();
        resolution = new javax.swing.JComboBox();
        audioenabled = new javax.swing.JCheckBox();
        frequency = new javax.swing.JComboBox();
        monoaudio = new javax.swing.JRadioButton();
        sterioaudio = new javax.swing.JRadioButton();
        lowquality = new javax.swing.JRadioButton();
        highquality = new javax.swing.JRadioButton();
        startbtn = new javax.swing.JButton();
        endbtn = new javax.swing.JButton();
        exitbtn = new javax.swing.JButton();
        pause = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Monito\u00aeCont\u00aeol");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Record Session");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel2.setText("Select Format");

        formatbox.setFont(new java.awt.Font("Tahoma", 1, 14));
        formatbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AVI", "QuickTime" }));
        formatbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatboxActionPerformed(evt);
            }
        });

        videoenabled.setFont(new java.awt.Font("Tahoma", 1, 14));
        videoenabled.setSelected(true);
        videoenabled.setText("Video");
        videoenabled.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        videoenabled.setMargin(new java.awt.Insets(0, 0, 0, 0));
        videoenabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videoenabledActionPerformed(evt);
            }
        });

        visualmode.setFont(new java.awt.Font("Tahoma", 1, 14));
        visualmode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RGB", "YUV" }));
        visualmode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visualmodeActionPerformed(evt);
            }
        });

        resolution.setFont(new java.awt.Font("Tahoma", 1, 14));
        resolution.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "160x120", "176x144", "320x240", "352x288", "640x480" }));
        resolution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resolutionActionPerformed(evt);
            }
        });

        audioenabled.setFont(new java.awt.Font("Tahoma", 1, 14));
        audioenabled.setSelected(true);
        audioenabled.setText("Audio");
        audioenabled.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        audioenabled.setMargin(new java.awt.Insets(0, 0, 0, 0));
        audioenabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioenabledActionPerformed(evt);
            }
        });

        frequency.setFont(new java.awt.Font("Tahoma", 1, 14));
        frequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "44100", "22050", "8000" }));

        monoaudio.setText("Mono");
        monoaudio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        monoaudio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        monoaudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monoaudioActionPerformed(evt);
            }
        });

        sterioaudio.setSelected(true);
        sterioaudio.setText("Sterio");
        sterioaudio.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sterioaudio.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sterioaudio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sterioaudioActionPerformed(evt);
            }
        });

        lowquality.setText("8-Bit");
        lowquality.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        lowquality.setMargin(new java.awt.Insets(0, 0, 0, 0));
        lowquality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowqualityActionPerformed(evt);
            }
        });

        highquality.setSelected(true);
        highquality.setText("16-Bit");
        highquality.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        highquality.setMargin(new java.awt.Insets(0, 0, 0, 0));
        highquality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highqualityActionPerformed(evt);
            }
        });

        startbtn.setText("Sta\u00aet");
        startbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startbtnActionPerformed(evt);
            }
        });

        endbtn.setText("End");
        endbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endbtnActionPerformed(evt);
            }
        });

        exitbtn.setText("Exit");
        exitbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitbtnActionPerformed(evt);
            }
        });

        pause.setText("P");
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(audioenabled)
                    .addComponent(resolution, 0, 211, Short.MAX_VALUE)
                    .addComponent(visualmode, 0, 211, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(formatbox, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(videoenabled, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(frequency, 0, 211, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lowquality, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monoaudio, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(sterioaudio)
                            .addComponent(highquality))
                        .addGap(8, 8, 8)
                        .addComponent(pause))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exitbtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(formatbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(videoenabled)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(visualmode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(audioenabled)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monoaudio)
                            .addComponent(sterioaudio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lowquality)
                            .addComponent(highquality)))
                    .addComponent(pause))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endbtn)
                    .addComponent(exitbtn)
                    .addComponent(startbtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitbtnActionPerformed
// TODO add your handling code here:
        if(processor.Started==1)
        processor.stop();
        processor.close();
    dispose();
    }//GEN-LAST:event_exitbtnActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
// TODO add your handling code here:
    if(!paused)    
    {
        processor.stop();
        paused = true;
        pause.setText("R");
    }else
    {
        processor.start();
        paused = false;
        pause.setText("P");
    }
    
        
    }//GEN-LAST:event_pauseActionPerformed

    private void endbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endbtnActionPerformed
// TODO add your handling code here:
        
        processor.stop();
	processor.close();
	datasink.close();
	processor = null;
	//buttonEnd.setEnabled(false);
	// Restart monitoring
	startTheStuff();
	//buttonStart.setLabel("Start");
	setDivices(true);
        startbtn.setEnabled(true);
        exitbtn.setEnabled(true);
        endbtn.setEnabled(false);
	System.out.println("Done saving.");
    }//GEN-LAST:event_endbtnActionPerformed

    public void setDivices(boolean val)
    {
        formatbox.setEnabled(val);
        videoenabled.setEnabled(val);
        visualmode.setEnabled(val);
        resolution.setEnabled(val);
        audioenabled.setEnabled(val);
        highquality.setEnabled(val);
        lowquality.setEnabled(val);
        sterioaudio.setEnabled(val);
        monoaudio.setEnabled(val);
        frequency.setEnabled(val);
    }
    
    
    private void startbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startbtnActionPerformed
// TODO add your handling code here:
        setDivices(false);
        startbtn.setEnabled(false);
        exitbtn.setEnabled(false);
        endbtn.setEnabled(true);
        endbtn.setEnabled(true);
        
        DataSource outputDS = processor.getDataOutput();
	try {
	    MediaLocator ml = new MediaLocator("file:capture." +
				 (outputType.equals("video.x_msvideo")? "avi" : "mov"));
	    datasink = Manager.createDataSink(outputDS, ml);
	    datasink.open();
	    datasink.start();
	} catch (Exception e) {
	    System.err.println(e);
	}
	processor.start();
	System.out.println("Started saving...");
    }//GEN-LAST:event_startbtnActionPerformed

    private void audioenabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioenabledActionPerformed
// TODO add your handling code here:
        startTheStuff();
    }//GEN-LAST:event_audioenabledActionPerformed

    private void resolutionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resolutionActionPerformed
// TODO add your handling code here:
        startTheStuff();
    }//GEN-LAST:event_resolutionActionPerformed

    private void visualmodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_visualmodeActionPerformed
// TODO add your handling code here:
        startTheStuff();
    }//GEN-LAST:event_visualmodeActionPerformed

    private void highqualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highqualityActionPerformed
// TODO add your handling code here:
        if(highquality.isSelected())
            lowquality.setSelected(false);
        else
            lowquality.setSelected(true);
        
        startTheStuff();
    }//GEN-LAST:event_highqualityActionPerformed

    private void lowqualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowqualityActionPerformed
// TODO add your handling code here:
        if(lowquality.isSelected())
            highquality.setSelected(false);
        else
            highquality.setSelected(true);
        
        startTheStuff();
    }//GEN-LAST:event_lowqualityActionPerformed

    private void sterioaudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sterioaudioActionPerformed
// TODO add your handling code here:
        if(sterioaudio.isSelected())
            monoaudio.setSelected(false);
        else
            monoaudio.setSelected(true);
        
        startTheStuff();
    }//GEN-LAST:event_sterioaudioActionPerformed

    private void monoaudioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monoaudioActionPerformed
// TODO add your handling code here:
        //System.out.println(monoaudio.isSelected());
        if(monoaudio.isSelected())
        {
            sterioaudio.setSelected(false);
        }
        else
            sterioaudio.setSelected(true);
        
        startTheStuff();
    }//GEN-LAST:event_monoaudioActionPerformed

    private void videoenabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_videoenabledActionPerformed
// TODO add your handling code here:
        startTheStuff();
    }//GEN-LAST:event_videoenabledActionPerformed

    private void formatboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatboxActionPerformed
// TODO add your handling code here:
        startTheStuff();
    }//GEN-LAST:event_formatboxActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visual().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox audioenabled;
    private javax.swing.JButton endbtn;
    private javax.swing.JButton exitbtn;
    private javax.swing.JComboBox formatbox;
    private javax.swing.JComboBox frequency;
    private javax.swing.JRadioButton highquality;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JRadioButton lowquality;
    private javax.swing.JRadioButton monoaudio;
    private javax.swing.JButton pause;
    private javax.swing.JComboBox resolution;
    private javax.swing.JButton startbtn;
    private javax.swing.JRadioButton sterioaudio;
    private javax.swing.JCheckBox videoenabled;
    private javax.swing.JComboBox visualmode;
    // End of variables declaration//GEN-END:variables
    
}
