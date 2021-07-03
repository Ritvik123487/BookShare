import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconnection{

    public Connection con;
    public Statement st;
    public ResultSet result;
    String URL = "jdbc:mysql://localhost:3306/Book_Share?serverTimezone=UTC"; 
    //JDBC is Java Database Connectivity
    String USER = "root";
    String PASSWORD = "abcschool";

    public Dbconnection(){

    try{

        System.out.println("Loading the Driver");
 
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 
        // connect to the database
        System.out.println("Connect to the database 1");
        con = DriverManager.getConnection(URL, USER, PASSWORD);

    }catch(Exception e){ // handle the SQLException

        
        e.printStackTrace();
    }
    }
}
