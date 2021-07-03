import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Book{

    public String isbn;
    public String title;
    public String subject;
    public String idowner;

    public Book(String argsisbn, String argstitle, String argssubject, String argsidowner){

        isbn = argsisbn;
        title = argstitle;
        subject = argssubject;
        idowner = argsidowner;

    }


    public void addBook(){

        Statement st;
        ResultSet result;
        int deleted;
        int x;
        ResultSet idmax;

       
       try{

           Dbconnection db = new Dbconnection();

           // create an SQL statement
           st = db.con.createStatement();

            int currentid = 0;
            idmax = st.executeQuery("select max(id) as maxid from book2");
            while(idmax.next()){

                currentid = idmax.getInt("maxid");
               
            }

            String add;
            add = ("insert into book2(id, title, subject, isbn, idowner) values("+ (currentid+1) + "," +
            "'" + title + "'" + "," + "'" + subject + "'" + "," + "'" + isbn + "'" + "," + "'" + idowner + "')");
            System.out.println(add);
            int updated;
            updated = st.executeUpdate(add);
            System.out.println(updated);

        }catch(Exception e){ // handle the SQLException
        
            e.printStackTrace();
        }



    }

    public static void delBook(){

        Statement st;
        ResultSet result;
        int deleted;
        int x;
        ResultSet idmax;

       
       try{

           Dbconnection db = new Dbconnection();

           // create an SQL statement
           st = db.con.createStatement();

            Scanner a = new Scanner(System.in);

            System.out.println("What is your id number ? ");

            x = a.nextInt();

            int currentid = 0;
            idmax = st.executeQuery("select * from book2 where idowner =" + x );
           System.out.println("Id     Title       Isbn     Subject      Idowner  " );

       while(idmax.next()){ // move to next record
        
        int booknumber = idmax.getInt("id");
        String title = idmax.getString("title");
        int isbn = idmax.getInt("isbn");
        String subject = idmax.getString("subject");
        int idowner = idmax.getInt("idowner");
        

        System.out.println(booknumber + "      " + title + "     " + isbn + "       " + subject + "           " 
        + idowner);
       }

       System.out.println("Which book id would you like to delete ? ");
        int ans = 0;
        ans = a.nextInt();
        deleted = st.executeUpdate("SET SQL_SAFE_UPDATES = 0;");
        deleted = st.executeUpdate("delete from book2 where id=" + ans);
        deleted = st.executeUpdate("delete from trans1 where Book_Number=" + ans);
        System.out.println("Book has been deleted");
           

        }catch(Exception e){ // handle the SQLException
        
            e.printStackTrace();
        }



    }

    public static void main(String[] args){

         Connection con;
         Statement st;
         ResultSet result;
         int deleted;

         String URL = "jdbc:mysql://localhost:3306/Book_Share?serverTimezone=UTC"; 
        //JDBC is Java Database Connectivity
        String USER = "root";
        String PASSWORD = "abcschool";
        int x;
        ResultSet idmax;

        
        try{

            System.out.println("Loading the Driver");
    
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    
            // connect to the database
            System.out.println("Connect to the database");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
    
            

            // create an SQL statement
            st = con.createStatement();

            Scanner a = new Scanner(System.in);

            System.out.println("What ID number do you want to access? ");

            x = a.nextInt();
                
            // execute an SQL query
            System.out.println("Execute the SQL query");
            result = st.executeQuery("select * from book2 where id =" + x);

            while(result.next()) // move to next record
            {
                int id = result.getInt("id");
                String title = result.getString("title");
                String subject = result.getString("subject");
                String isbn = result.getString("isbn");
                int idowner = result.getInt("idowner");

                System.out.println(id + ", " + title + ", " + subject + ", " + isbn + ", " + idowner);
            }

            // Inserting Records
            int currentid = 0;
            idmax = st.executeQuery("select max(id) as maxid from book2");
            while(idmax.next()){

                currentid = idmax.getInt("maxid");
               
            }
            System.out.println("Insert a book");
            String currenttitle;
            System.out.println("Title");
            currenttitle = a.next();

            String currentsubject;
            System.out.println("What is the book subject");
            currentsubject = a.next();

            String currentisbn;
            System.out.println("What is the isbn");
            currentisbn = a.next();

            String currentidowner;
            System.out.println("What is your id number");
            currentidowner = a.next();

            //add record

            String add;
            add = ("insert into book(id, title, subject, isbn, idowner) values("+ (currentid+1) + "," +
            "'" + currenttitle + "'" + "," + "'" + currentsubject + "'" + "," + "'" + currentisbn + "'" + "," + "'" + currentidowner + "')");
            System.out.println(add);
            int updated;
            updated = st.executeUpdate(add);
            System.out.println(updated);

            //Deleting
            int did;
            System.out.println("What id number do you want to delete ?");
            did = a.nextInt();
            deleted = st.executeUpdate("delete from book2 where id=" + did);
            System.out.println("Record deleted"+deleted);

           


        }catch(Exception e){ // handle the SQLException
        
            e.printStackTrace();
        }


    }

    public String toString(){

        return ("Subject: " + this.subject + "; " + "Title: " + this.title + "; " + "Isbn: " + this.isbn + "; ");


    }
}