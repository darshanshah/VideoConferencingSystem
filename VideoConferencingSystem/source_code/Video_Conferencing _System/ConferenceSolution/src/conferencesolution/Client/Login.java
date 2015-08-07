
package conferencesolution.Client;


import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.net.*;
import java.io.*;

public class Login extends JFrame implements ActionListener,Serializable
{
  JTextField txtUsername, txtServer, txtPort;
  JLabel     lblStatus;
  JButton    btnLogin, btnCancel;
  Socket     sckConnect;
  boolean    bConnected = false;
  private    LoginListener listener;

  public Login(LoginListener l, String title)
  {   
    super(title);
    try
    {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        
    } catch(Exception e)
    {
        
    }
    this.getContentPane().setBackground(new Color(236,233,216));
    listener = l;
    screenSetup();
    setResizable(false);
    pack();
    
    // Treat pressing the [x] as pressing cancel
    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        listener.onLoginCancel();
      }
    });
  }

  public void screenSetup()
  {
    JLabel lblUsername = new JLabel("Username: ");
    txtUsername = new JTextField(10);
    lblUsername.setLabelFor(txtUsername);
    lblStatus = new JLabel("Not connected");
    lblStatus.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
    
    // Create a frame pane for the login details
    JPanel loginControlsPane = new JPanel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    loginControlsPane.setLayout(gridbag);

    JLabel[] labels = {lblUsername};
    JTextField[] textFields = {txtUsername};
    addLabelTextRows(labels, textFields, gridbag, loginControlsPane);

    c.gridwidth = GridBagConstraints.REMAINDER;
    c.anchor = GridBagConstraints.WEST;
    c.weightx = 1.0;
    gridbag.setConstraints(lblStatus, c);
    loginControlsPane.add(lblStatus);
    loginControlsPane.setBorder(
      BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Login"),
        BorderFactory.createEmptyBorder(5,5,5,5)));

    // Create the server settings controls
    JLabel lblServer = new JLabel("Server address: ");
    try
    {
    txtServer = new JTextField(10);
    txtServer.setText(InetAddress.getLocalHost().getHostAddress());
    } catch (Exception e)
    {
        
    }
    JLabel lblPort = new JLabel("Server port: ");
    txtPort = new JTextField("1000", 5);
    lblServer.setLabelFor(txtServer);
    lblPort.setLabelFor(txtPort);

    // Create a frame pane for the server details
    JPanel serverControlsPane = new JPanel();
    GridBagLayout gridbag2 = new GridBagLayout();
    GridBagConstraints c2 = new GridBagConstraints();

    serverControlsPane.setLayout(gridbag2);

    JLabel[] labels2 = {lblServer, lblPort};
    JTextField[] textFields2 = {txtServer, txtPort};
    addLabelTextRows(labels2, textFields2, gridbag2, serverControlsPane);

    c2.gridwidth = GridBagConstraints.REMAINDER;
    c2.anchor = GridBagConstraints.WEST;
    c2.weightx = 1.0;
    serverControlsPane.setBorder(
      BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("Server Settings"),
        BorderFactory.createEmptyBorder(5,5,5,5)));

    // Create the login and cancel buttons
    btnLogin = new JButton("Login");
    btnLogin.addActionListener(this);
    btnCancel = new JButton("Cancel");
    btnCancel.addActionListener(this);

    // Create a pane and add the buttons next to each other
    JPanel buttonPane = new JPanel();
    buttonPane.add(btnLogin);
    buttonPane.add(btnCancel);

    JPanel pane = new JPanel();
    BoxLayout mainbox = new BoxLayout(pane, BoxLayout.Y_AXIS);
    pane.setLayout(mainbox);
    pane.add(loginControlsPane);
    pane.add(serverControlsPane);
    pane.add(buttonPane);
    pane.setBorder(
      BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(10,10,10,10),
        BorderFactory.createEmptyBorder(0,0,0,0)));
    setContentPane(pane);
    
    
    this.setLocation(400,250);
    
  }

  private void addLabelTextRows(JLabel[] labels,
                                JTextField[] textFields,
                                GridBagLayout gridbag,
                                Container container)
  {
    GridBagConstraints c = new GridBagConstraints();
    c.anchor = GridBagConstraints.EAST;
    int numLabels = labels.length;

    for (int i = 0; i < numLabels; i++)
    {
      c.gridwidth = GridBagConstraints.RELATIVE;
      c.fill = GridBagConstraints.NONE;
      c.weightx = 0.0;
      gridbag.setConstraints(labels[i], c);
      container.add(labels[i]);

      c.gridwidth = GridBagConstraints.REMAINDER;
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 1.0;
      gridbag.setConstraints(textFields[i], c);
      container.add(textFields[i]);
    }
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource() == btnCancel)
    {
      listener.onLoginCancel();
    }
    if(e.getSource() == btnLogin)
    {
      int port = 0;
      // Make sure there is a username
      if(txtUsername.getText().equals(""))
      {
        JOptionPane.showMessageDialog(null, "Username required", "Login error", JOptionPane.ERROR_MESSAGE);
      }
      else
      {
        try
        {
          port = Integer.parseInt(txtPort.getText());
         // System.out.println("Requesting...");
          listener.onConnectRequest(txtServer.getText(), port, txtUsername.getText()); 
        }
        catch(NumberFormatException err)
        {
          JOptionPane.showMessageDialog(null, "Invalid port number", "Login error", JOptionPane.ERROR_MESSAGE); 
        }
      }
    }
  }

  public void setStatus(String status)
  {
    lblStatus.setText(status);
  }
}