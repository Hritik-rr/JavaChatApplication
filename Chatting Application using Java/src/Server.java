package chatting.application.using.java;
import javax.swing.*; //Swing Package consist of JFrame class
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener{
    //All frame related coding will be under constructor
    //Because we want the Frame to open at the point we run the class
    
    //Global Declaration for global scope
    JPanel p1; //JPanel Creates a Division for the header in the frame
    JTextField t1; //JTextField for putting the text
    JButton b1; //JButton for clicking the button
    static JTextArea a1; //For reflecting the messages after sending in some text area
    
    
    //ServerSocket and Socket object
    //These comes under the .net packages
    static ServerSocket skt;
    static Socket s;
    //Data input/output object
    //This will track the I/O stream of messages
    static DataInputStream din;
    static DataOutputStream dout;

    
    Server(){
        
        
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
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/GudduPandit.png"));
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
        JLabel l3 = new JLabel("Guddu");
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
        setLocation(400, 200);
        
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
            dout.writeUTF(out);
            t1.setText("");
            
        } catch(Exception e) {
            
        }
    }
    
 
    public static void main(String[] args){
        new Server().setVisible(true);
        
        String msginput = "";
        
        try{
            //Socket class objectskt
            skt = new ServerSocket(6001);
            //Server class Object which will accept the data
            s = skt.accept();
            
            din = new DataInputStream(s.getInputStream()); //All the data will come in with the help of socket
            dout = new DataOutputStream(s.getOutputStream()); //All the data will go out with the help of socket
            
            //readUTF() for reading the message input stream
            msginput = din.readUTF();
            a1.setText(a1.getText() + "\n" + msginput);
            
            //Closing the server socket and socket connections 
            skt.close();
            s.close();
            
        } catch(Exception e) {
            
        }
    }
}

//----------------------------------------------------------------------
//package chatting.application;
//
//import javax.swing.*;
//import javax.swing.border.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.net.*;
//import java.io.*;
//
//import java.util.Calendar;
//import java.text.SimpleDateFormat;
//
//public class Server implements ActionListener{
//    
//    JPanel p1;
//    JTextField t1;
//    JButton b1;
//    static JPanel a1;
//    static JFrame f1 = new JFrame();
//    
//    static Box vertical = Box.createVerticalBox();
//    
//    static ServerSocket skt;
//    static Socket s;
//    static DataInputStream din;
//    static DataOutputStream dout;
//    
//    Boolean typing;
//    
//    Server(){
//        f1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        p1 = new JPanel();
//        p1.setLayout(null);
//        p1.setBackground(new Color(7, 94, 84));
//        p1.setBounds(0, 0, 450, 70);
//        f1.add(p1);
//        
//       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/3.png"));
//       Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//       ImageIcon i3 = new ImageIcon(i2);
//       JLabel l1 = new JLabel(i3);
//       l1.setBounds(5, 17, 30, 30);
//       p1.add(l1);
//       
//       l1.addMouseListener(new MouseAdapter(){
//           public void mouseClicked(MouseEvent ae){
//               System.exit(0);
//           }
//       });
//       
//       ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/GudduPandit.png"));
//       Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
//       ImageIcon i6 = new ImageIcon(i5);
//       JLabel l2 = new JLabel(i6);
//       l2.setBounds(40, 5, 60, 60);
//       p1.add(l2);
//       
//       ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/video.png"));
//       Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
//       ImageIcon i9 = new ImageIcon(i8);
//       JLabel l5 = new JLabel(i9);
//       l5.setBounds(290, 20, 30, 30);
//       p1.add(l5);
//       
//       ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/phone.png"));
//       Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
//       ImageIcon i13 = new ImageIcon(i12);
//       JLabel l6 = new JLabel(i13);
//       l6.setBounds(350, 20, 35, 30);
//       p1.add(l6);
//       
//       ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/using/java/Icons/3icon.png"));
//       Image i15 = i14.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
//       ImageIcon i16 = new ImageIcon(i15);
//       JLabel l7 = new JLabel(i16);
//       l7.setBounds(410, 20, 13, 25);
//       p1.add(l7);
//       
//       
//       JLabel l3 = new JLabel("Gaitonde");
//       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
//       l3.setForeground(Color.WHITE);
//       l3.setBounds(110, 15, 100, 18);
//       p1.add(l3);   
//       
//       
//       JLabel l4 = new JLabel("Active Now");
//       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
//       l4.setForeground(Color.WHITE);
//       l4.setBounds(110, 35, 100, 20);
//       p1.add(l4);  
//       
//       Timer t = new Timer(1, new ActionListener(){
//           public void actionPerformed(ActionEvent ae){
//               if(!typing){
//                   l4.setText("Active Now");
//               }
//           }
//       });
//       
//       t.setInitialDelay(2000);
//       
//       
//       a1 = new JPanel();
//       a1.setBounds(5, 75, 440, 570);
//       a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
//       f1.add(a1);
//       
//       
//       t1 = new JTextField();
//       t1.setBounds(5, 655, 310, 40);
//       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
//       f1.add(t1);
//       
//       t1.addKeyListener(new KeyAdapter(){
//           public void keyPressed(KeyEvent ke){
//               l4.setText("typing...");
//               
//               t.stop();
//               
//               typing = true;
//           }
//           
//           public void keyReleased(KeyEvent ke){
//               typing = false;
//               
//               if(!t.isRunning()){
//                   t.start();
//               }
//           }
//       });
//       
//       b1 = new JButton("Send");
//       b1.setBounds(320, 655, 123, 40);
//       b1.setBackground(new Color(7, 94, 84));
//       b1.setForeground(Color.WHITE);
//       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
//       b1.addActionListener(this);
//       f1.add(b1);
//        
//       f1.getContentPane().setBackground(Color.WHITE);
//       f1.setLayout(null);
//       f1.setSize(450, 700);
//       f1.setLocation(400, 200); 
//       f1.setUndecorated(true);
//       f1.setVisible(true);
//        
//    }
//    
//    public void actionPerformed(ActionEvent ae){
//        try{
//            String out = t1.getText();
//            
//            JPanel p2 = formatLabel(out);
//            
//            a1.setLayout(new BorderLayout());
//            
//            JPanel right = new JPanel(new BorderLayout());
//            right.add(p2, BorderLayout.LINE_END);
//            vertical.add(right);
//            vertical.add(Box.createVerticalStrut(15));
//            
//            a1.add(vertical, BorderLayout.PAGE_START);
//            
//            //a1.add(p2);
//            dout.writeUTF(out);
//            t1.setText("");
//        }catch(Exception e){
//            System.out.println(e);
//        }
//    }
//    
//    public static JPanel formatLabel(String out){
//        JPanel p3 = new JPanel();
//        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
//        
//        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
//        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        l1.setBackground(new Color(37, 211, 102));
//        l1.setOpaque(true);
//        l1.setBorder(new EmptyBorder(15,15,15,50));
//        
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        
//        JLabel l2 = new JLabel();
//        l2.setText(sdf.format(cal.getTime()));
//        
//        p3.add(l1);
//        p3.add(l2);
//        return p3;
//    }
//    
//    public static void main(String[] args){
//        new Server().f1.setVisible(true);
//        
//        String msginput = "";
//        try{
//            skt = new ServerSocket(6001);
//            while(true){
//                s = skt.accept();
//                din = new DataInputStream(s.getInputStream());
//                dout = new DataOutputStream(s.getOutputStream());
//            
//	        while(true){
//	                msginput = din.readUTF();
//                        JPanel p2 = formatLabel(msginput);
//                        
//                        JPanel left = new JPanel(new BorderLayout());
//                        left.add(p2, BorderLayout.LINE_START);
//                        vertical.add(left);
//                        f1.validate();
//            	}
//                
//            }
//            
//        }catch(Exception e){}
//    }    
//}