import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Member{
    public Connection con;
    public Statement st;
    public ResultSet result;
    public ResultSet deleted;

    public String email;
    public long phone_number;
    public String Address;
    public String lname;
    public String fname;

    public Member(String argsemail, long argiphone_number, String argslname, String argsfname){

        email = argsemail;

        phone_number = argiphone_number;

        lname = argslname;

        fname = argsfname;

    }

    public void addMember(){

        try{

            Dbconnection db = new Dbconnection();
            st = db.con.createStatement();

            // Inserting Records
            int currentid = 0;
            ResultSet idmax;
            idmax = st.executeQuery("select max(id) as maxid from member1");
            while(idmax.next()){

                currentid = idmax.getInt("maxid");
            
            }

            //add record

            String add;
            add = ("insert into member1(id, firstname, lastname, phone, email) values("+ (currentid+1) + "," +
            "'" + fname + "'" + "," + "'" + lname + "'" + "," + "'" + phone_number + "'" + "," + "'" + email + "')");
            System.out.println(add);
            int updated;
            updated = st.executeUpdate(add);
            System.out.println(updated);

        }catch(Exception e){ // handle the SQLException
                
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}