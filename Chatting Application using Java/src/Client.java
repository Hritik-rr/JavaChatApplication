package chatting.application.using.java;
import javax.swing.*; //Swing Package consist of JFrame class
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Client extends JFrame implements ActionListener{
    //All frame related coding will be under constructor
    //Because we want the Frame to open at the point we run the class
    
    //Global Declaration for global scope
    JPanel p1; //JPanel Creates a Division for the header in the frame
    JTextField t1; //JTextField for putting the text
    JButton b1; //JButton for clicking the button
    static JTextArea a1; //For reflecting the messages after sending in some text area
    
    //Socket class object
    static Socket s;
    
    //Data input/output object
    //This will track the I/O stream of messages
    static DataInputStream din;
    static DataOutputStream dout;
    
    Client(){
        
        
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);
      
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/3.png"));//
        Image i2 = i1.getImage().getScaledInstance(22, 22, Image.SCALE_DEFAULT);//Used for changing the image layout
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 21, 22, 22);
        p1.add(l1);
        
        //addMouseListener catches the particular event of the mouse clicked and perform the task
        l1.addMouseListener(new MouseAdapter(){
             public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/KaleemBhaiya.png"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 5, 60, 60);
        p1.add(l2);


        //For the video icon
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(310, 25, 25, 25);
        p1.add(l5);
        
        
        //For the photo icon
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/phone.png"));
        Image i12 = i11.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(365, 25, 25, 25);
        p1.add(l6);
        

        //For the other icon
        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/3icon.png"));
         Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(410, 25, 13, 25);
        p1.add(l7);

        
        //Name
        JLabel l3 = new JLabel("Kaleen Bhaiya");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 18);
        p1.add(l3);

        
        //Activity Status
        JLabel l4 = new JLabel("Online");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(112, 35, 100, 20);
        p1.add(l4);       
        
        
        //Text Area
        a1 = new JTextArea();
        a1.setBounds(5, 75, 440, 572);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
//        a1.setBackground(Color.WHITE);
        add(a1);
        
        
        //Text Field for written text to be reflected
        t1 = new JTextField();
        t1.setBounds(5, 655, 310, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        
        //Text Button for written text to be reflected
        b1 = new JButton("Send");
        b1.setBounds(320, 655, 123, 40);
        b1.setBackground(new Color(7, 94, 84)); //BG Color change for the text button
        b1.setForeground(Color.WHITE); //Foregorund color
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this); //Whenever I click it, an action is performed
        add(b1);
        
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(450, 700);
        setLocation(1200, 200);
        
        //For removing the cross/minimize bar
        setUndecorated(true);
        setVisible(true); //For changing the frame
        
    } 
    
    //actionPerformed(ActionEvent ae) function extracts the text from the textField
    //and puts it in the text area
    public void actionPerformed(ActionEvent ae){
        try{
            String out = t1.getText();
            a1.setText(a1.getText() + "\n\t\t\t" + out);
            dout.writeUTF(out); //For sending the text to the user
            t1.setText("");
        } catch(Exception e){
            
        }
    }
    
 
    public static void main(String[] args){
        new Client().setVisible(true);
        
        
        try {
            //Passing the server IP address, here we are using local host
            //So writing the local host IP address
            
            //Also putting in the port number, since we are connecting to the same
            //server, so giving the same port number given in the server class
            s = new Socket("127.0.0.1", 6001);

            din = new DataInputStream(s.getInputStream()); //All the data will come in with the help of socket
            dout = new DataOutputStream(s.getOutputStream()); //All the data will go out with the help of socket
            
            String msginput = "";
            
            //readUTF() for reading the message input stream
            msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
            
        } catch(Exception e) {
            
        }
    }
}
