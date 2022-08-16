import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertUpdateData {
    static String DB_URL = "jdbc:mysql://localhost/TicketBooking";
    static final String USER = "root";
    static final String PASS = "root";
    public static void insertData(String seat_type, int id, int no_seat, int price, int bookNoSeat, int amount) {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            if (seat_type.equals("E")) {
                String sql = "INSERT INTO Economy_Table (P_Id,No_Seat,E_Price,Book_No_Seat,Amount) VALUES("+id+"," + no_seat + "," + price + "," + bookNoSeat + "," + amount + ")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else if (seat_type.equals("B")) {
                String sql = "INSERT INTO Business_Table (P_Id,No_Seat,B_Price,Book_No_Seat,Amount) VALUES("+id+"," + no_seat + "," + price + "," + bookNoSeat + "," + amount + ")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else {
                String sql = "INSERT INTO Takal_Table (P_Id,No_Seat,T_Price,Book_No_Seat,Amount) VALUES("+id+"," + no_seat + "," + price + "," + bookNoSeat + "," + amount + ")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateData(String seat_type,int no_seat,int price,int amount,int p_id) {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            if (seat_type.equals("E")) {
                String sql = "UPDATE Economy_Table SET No_Seat =" + no_seat + ",E_Price = " + price + ",Amount = " + amount + " Where P_Id = " + p_id + "";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else if (seat_type.equals("B")) {
                String sql = "UPDATE Business_Table SET No_Seat =" + no_seat + ",B_Price = " + price + ",Amount = " + amount + " Where P_Id = " + p_id + "";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else {
                String sql = "UPDATE Takal_Table SET No_Seat =" + no_seat + ",T_Price = " + price + ",Amount = " + amount + " Where P_Id = " + p_id + "";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void bookingData(int booking_id, int f_type, String seat_type,int p_id) {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            if (seat_type.equals("E")) {
                String sql = "INSERT INTO Book_Table VALUES("+booking_id+","+f_type+",'"+seat_type+"',"+p_id+")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else if (seat_type.equals("B")) {
                String sql = "INSERT INTO Book_Table VALUES("+booking_id+","+f_type+",'"+seat_type+"',"+p_id+")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            } else {
                String sql = "INSERT INTO Book_Table VALUES("+booking_id+","+f_type+",'"+seat_type+"',"+p_id+")";
                statement.executeUpdate(sql);
                System.out.println("-----------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insert(int p_id,String name ,int age,String pass){
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO PASSENGER_Table1 VALUES ("+p_id+",'"+name+"',"+age+",'"+pass+"')";
            statement.executeUpdate(sql);
            System.out.println("Thank you...");
            System.out.println("----------YOU HAVE REGISTERED SUCCESSFULLY-----------");
            System.out.println("\t Register Number : "+p_id);
            System.out.println(" >>>>>>>>>Choose your choice<<<<<<<<<<< ");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
