import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseClass {
    public static void main(String[] args){
        String DB_URL = "jdbc:mysql://localhost/";
        final String USER = "root";
        final String PASS = "root";
      try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = conn.createStatement();
            String sql = "CREATE DATABASE TicketBooking";
            statement.executeUpdate(sql);
            System.out.println("DataBase Successfully created...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            DB_URL += "TicketBooking";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PASSENGER_Table1(P_Id INTEGER NOT NULL PRIMARY KEY,P_Name VARCHAR(50)NOT NULL,Age INTEGER(5),Password VARCHAR(10) NOT NULL)";
            statement.executeUpdate(sql);
            System.out.println("Passenger table is Successfully created...");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            DB_URL += "TicketBooking";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Book_Table(Booking_Id INTEGER NOT NULL,F_Type INTEGER NOT NULL , Seat_Type VARCHAR(50) NOT NULL,P_Id INTEGER NOT NULL,PRIMARY KEY(Booking_Id),FOREIGN KEY(P_Id) REFERENCES PASSENGER_Table1(P_Id))";
            statement.executeUpdate(sql);
            System.out.println("Booking table is Successfully created...");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        try{
            DB_URL += "TicketBooking";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Economy_Table(E_Id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,P_Id INTEGER NOT NULL,No_Seat INTEGER NOT NULL,E_Price INTEGER NOT NULL,Book_No_Seat INTEGER NOT NULL,Amount INTEGER NOT NULL,FOREIGN KEY(P_Id) REFERENCES PASSENGER_Table1(P_Id))";
            statement.executeUpdate(sql);
            String sql1 = "CREATE TABLE IF NOT EXISTS Business_Table(B_Id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,P_Id INTEGER NOT NULL,No_Seat INTEGER NOT NULL,B_Price INTEGER NOT NULL,Book_No_Seat INTEGER NOT NULL,Amount INTEGER NOT NULL,FOREIGN KEY(P_Id) REFERENCES PASSENGER_Table1(P_Id))";
            statement.executeUpdate(sql1);
            String sql2 = "CREATE TABLE IF NOT EXISTS Takal_Table(T_Id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,P_Id INTEGER NOT NULL,No_Seat INTEGER NOT NULL,T_Price INTEGER NOT NULL,Book_No_Seat INTEGER NOT NULL,Amount INTEGER NOT NULL,FOREIGN KEY(P_Id) REFERENCES PASSENGER_Table1(P_Id))";
            statement.executeUpdate(sql2);
            System.out.println("All table  is Successfully created...");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
