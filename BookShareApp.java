import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;    
import javax.swing.JButton;    
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.awt.Container;

public class BookShareAppBut{

    public Connection con;
    public Statement st;
    public ResultSet result;
    public ResultSet deleted;
    private JFrame frame;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private int width;
    private int height;

    public BookShareAppBut(int x, int y){

        width = x;
        height = y;
        frame = new JFrame();
        button1 = new JButton("Add Member");
        button2 = new JButton("Add Book");
        button3 = new JButton("Check Out");
        button4 = new JButton("Return Book");
        button5 = new JButton("Check Return Date");
        button6 = new JButton("Delete Book");

   }

   public void setup(){
    Container x = frame.getContentPane(); //Gets the pane for the frame object
    FlowLayout fl = new FlowLayout(); // Helps to oreintate the buttins well
    x.setLayout(fl);
    frame.setTitle("Book Share App");
    frame.setSize(width, height);  
    x.add(button1);
    x.add(button2);
    x.add(button3);
    x.add(button4);
    x.add(button5);
    x.add(button6);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
   }
  public void setListen(){
   ActionListener buttonListener = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
            plusMember();
           }
   };
   ActionListener buttonListener2 = new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent ae){
        plusBook();
       }
   };

   ActionListener buttonListener3 = new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae){
        checkout();
    }
};

ActionListener buttonListener4 = new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae){
        returnbook();
    }
};

ActionListener buttonListener5 = new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent ae){
        dateback();
    }
};

   ActionListener buttonListener6 = new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent ae){
            delBook();
           }
   };

       button1.addActionListener(buttonListener);
       button2.addActionListener(buttonListener2);
       button3.addActionListener(buttonListener3);
       button4.addActionListener(buttonListener4);
       button5.addActionListener(buttonListener5);
       button6.addActionListener(buttonListener6);
  }

  public static void delBook(){
      delBook();

  }

   public static void returnbook(){

    Scanner a = new Scanner(System.in);
    int perid;
    System.out.println("What is your ID ? ");
    perid = a.nextInt();

    Trans.returndate(perid);

   }

   public static void dateback(){

    int personid;
    Scanner a = new Scanner(System.in);
    System.out.println("What is your ID ? ");
    personid = a.nextInt();

    Trans.getReturnDate(personid);

   }

   public static void checkout(){

    String currentstatus = "True";
    String year;
    int month;
    String daycur;
    String date;
    String dayback;
    int personid;

    Scanner a = new Scanner(System.in);

    System.out.println("What is your ID ? ");

    personid = a.nextInt();

    System.out.println("What is the year - Last 2 digits ? ");

    year = a.next();

    System.out.println("What is the month ? ");

    month = a.nextInt();

    System.out.println("What is the day ? ");

    daycur = a.next();

    date = year + "-" + month + "-" + daycur;

    month = month + 2;

    String.valueOf(month);

    dayback = year + "-" + (month) + "-" + daycur;

    Trans ts = new Trans(personid, currentstatus, date, dayback);

    ts.takeOut();


   }

   public static void plusBook(){

    Scanner a = new Scanner(System.in);

    String currentisbn;
    System.out.println("What is the ISBN of the book ? ");
    currentisbn = a.next();

    String currenttitle;
    System.out.println("What is the Title of the Book ? ");
    currenttitle = a.next();

    String currentsubject;
    System.out.println("What is the subject of the Book ? ");
    currentsubject = a.next();

    String currentid;
    System.out.println("What is your ID ? ");
    currentid = a.next();

    Book bk = new Book(currentisbn, currenttitle, currentsubject, currentid);

    bk.addBook();
    
   }

   public static void plusMember(){

    Scanner a = new Scanner(System.in);

    String currentfname;
    System.out.println("What is your First Name ? ");
    currentfname = a.next();

    String currentlname;
    System.out.println("What is your Last Name ? ");
    currentlname = a.next();

    long currentphone;
    System.out.println("What is your PhoneNumber ? ");
    currentphone = a.nextLong();

    String currentemail;
    System.out.println("What is your Email ? ");
    currentemail = a.next();

    System.out.println(currentemail+ currentphone+ currentlname+ currentfname);

    Member mem = new Member(currentemail, currentphone, currentlname, currentfname);

    mem.addMember();

   }

    public static void main(String[] args){
        BookShareAppBut bd = new BookShareAppBut(400,100);
        bd.setup();
        bd.setListen();
    
    }

}


