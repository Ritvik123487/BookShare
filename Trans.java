import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Trans{

    public int ids;
    public static int booknumber = 0;
    public static int count=0;
    public String currentstatus;
    public String date;
    public String dayback; 

public Trans(int argiids, String argsstatus, String argsdate, String argsdayback){

    ids = argiids;
    currentstatus = argsstatus;
    date = argsdate;
    dayback = argsdayback;
}

public static void getReturnDate(int personid){

     Statement st;
     ResultSet result;

   try{

        Dbconnection db = new Dbconnection();

       // create an SQL statement
       st = db.con.createStatement();

       Scanner a = new Scanner(System.in);
       
       // execute an SQL query
       result = st.executeQuery("select * from trans1 where person =" + personid + 
       " and status = 'True' ");

       System.out.println("Book_Number    Id    Status       Checkin Date          Checkout Date            Transaction Id" );

       while(result.next()){ // move to next record
        
        int booknumber = result.getInt("Book_Number");
        int id = result.getInt("Person");
        String status = result.getString("Status");
        String checkin = result.getString("Checkin");
        checkin = checkin.substring(0,11);
        String checkout = result.getString("Checkout");
        checkout = checkout.substring(0,11);
        int transid = result.getInt("Transid");
        
        if((transid>0)){
            count++;
        }
        System.out.println("      "+booknumber + "         " + id + "     " + status + "         " + checkin + "            " 
        + checkout + "                   " + transid);

        

    }
    if(count==0){
        System.out.println("You have returned all your books!");
    }

    }catch(Exception e){ // handle the SQLException

        
        e.printStackTrace();
    }

}

public static void returndate(int person){
    Statement st;
    ResultSet result;
    int update;
    
   try{

       Dbconnection db = new Dbconnection();

       Scanner a = new Scanner(System.in);

       // create an SQL statement
       st = db.con.createStatement();

       result = st.executeQuery("select * from trans1 where person =" + person + 
       " and status = 'True' ");

       System.out.println("Book_Number    Id    Status       Checkin Date          Checkout Date            Transaction Id" );

       while(result.next()){ // move to next record
        
        booknumber = result.getInt("Book_Number");
        int id = result.getInt("Person");
        String status = result.getString("Status");
        String checkin = result.getString("Checkin");
        checkin = checkin.substring(0,11);
        String checkout = result.getString("Checkout");
        checkout = checkout.substring(0,11);
        int transid = result.getInt("Transid");
       

        System.out.println("      "+booknumber + "         " + id + "     " + status + "         " + checkin + "            " 
        + checkout + "                   " + transid);
       }

       if (booknumber!=0){
        System.out.println("Which Book do you want to return ?");
        System.out.println("Please provide the Transaction ");
        int breturn;
        breturn = a.nextInt();

        String add;
        add = ("update trans1 set Status = 'False' where Transid = "+ breturn);
        update = st.executeUpdate(add);
        System.out.println("You have returned the book, have a nice day");
       }
       else{
           System.out.println("All books have already been returned");
       }
    
    }catch(Exception e){ // handle the SQLException

        
        e.printStackTrace();
    }

}

public void takeOut(){

    Statement st;
    ResultSet result;

   try{

       Dbconnection db = new Dbconnection();
       // create an SQL statement
       st = db.con.createStatement();

       int currentid = 0;
        ResultSet idmax;
        idmax = st.executeQuery("select max(id) as maxid from member1");
        while(idmax.next()){

        currentid = idmax.getInt("maxid");
    
        }

        result = st.executeQuery("select * from book2");

        System.out.println("Id     Title       Isbn     Subject      Idowner  " );

       while(result.next()){ // move to next record
        
        booknumber = result.getInt("id");
        String title = result.getString("title");
        int isbn = result.getInt("isbn");
        String subject = result.getString("subject");
        int idowner = result.getInt("idowner");
        

        System.out.println(booknumber + "      " + title + "     " + isbn + "       " + subject + "           " 
        + idowner);
       }

       Scanner a = new Scanner(System.in);
       System.out.println("Which Book Id Would you like to check out ? ");
       int ans = a.nextInt();
       String add;

       add = ("insert into trans1(Book_Number, Person, Status, Checkin, Checkout) values("+ (ans) + ","
            + ids + "," + "'" + currentstatus + "'" + "," + "'" + date + 
            "'" + "," + "'" + dayback + "')");
            System.out.println(add);
            int updated;
            updated = st.executeUpdate(add);
            System.out.println(updated);
            System.out.println("Please return the book on " + dayback);

    }catch(Exception e){ // handle the SQLException

        
        e.printStackTrace();
    }

}

 public static void main(String[] args){

    Connection con;
    Statement st;
    ResultSet result;
    int ids;

    String URL = "jdbc:mysql://localhost:3306/Book_Share?serverTimezone=UTC"; 
    //JDBC is Java Database Connectivity
    String USER = "root";
    String PASSWORD = "abcschool";
    
   //Are from the import commands

   try{

       System.out.println("Loading the Driver");

       Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

       // connect to the database
       System.out.println("Connect to the database 1");
       con = DriverManager.getConnection(URL, USER, PASSWORD);

       // create an SQL statement
       st = con.createStatement();

       int lend = 0;

       Scanner a = new Scanner(System.in);

        while(lend == 0){

            System.out.println("Which book would you like to borrow - Book ID");

            ids = a.nextInt();

            // execute an SQL query
            System.out.println("Execute the SQL query");
            result = st.executeQuery("select * from book2 where id =" + ids);

            int idowner = 0;
            String subject = "";

            while(result.next()){ // move to next record
            
                int id = result.getInt("id");
                String title = result.getString("title");
                subject = result.getString("subject");
                String isbn = result.getString("isbn");
                idowner = result.getInt("idowner");

                System.out.println(id + ", " + title + ", " + subject + ", " + isbn + ", " + idowner);
            }

            System.out.println("This is the book you have chosen");

            String currentstatus = "True";
            String add;
            String year;
            int month;
            String daycur;
            String date;
            String dayback;

            System.out.println("What is the year - Last 2 digits");

            year = a.next();

            System.out.println("What is the month");

            month = a.nextInt();

            System.out.println("What is the day");

            daycur = a.next();

            date = year + "-" + month + "-" + daycur;

            month = month + 2;

            String.valueOf(month);

            dayback = year + "-" + (month) + "-" + daycur;

            add = ("insert into trans1(Book_Number, Person, Status, Checkin, Checkout) values("+ ids + "," +
            "'" + idowner + "'" + "," + "'" + currentstatus + "'" + "," + "'" + date + 
            "'" + "," + "'" + dayback + "')");
            System.out.println(add);
            int updated;
            updated = st.executeUpdate(add);
            System.out.println(updated);
            System.out.println("Please return the book on " + dayback);

            String lending;

                System.out.println("Would you like to borrow another book ?");

                lending = a.next();

                if(lending.equals("no")){

                    lend = 1;
                }


        }

        System.out.println("Ending the Program");
    }catch(Exception e){ // handle the SQLException

        
            e.printStackTrace();
        }

         


 }
     


}
    



