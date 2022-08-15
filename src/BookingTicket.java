import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class BookingTicket {
    Scanner scanner = new Scanner(System.in);
    String DB_URL = "jdbc:mysql://localhost/TicketBooking";
    final String USER = "root";
    final String PASS = "root";
    int e_seat = 10,b_seat = 15, e_price = 5500 , b_price = 5000 , t_price = 5500 , t_seat = 5;
    String seat_type ="";
    int bookNoSeat , temp , amount , booking_id ,balance ,p_id;
    public void book(){
        System.out.println("Enter the Passenger Id");
        p_id = scanner.nextInt();
        System.out.println("Available Flights : \n *101 (Madurai to Chennai) \n *102 (Mumbai to Chennai)");
        int f_type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("* Economy class   * Business class (enter E/B)");
        seat_type = scanner.nextLine();
        if(seat_type.equals("E")){
            System.out.println("Available Seats : Economy -> "+e_seat);
            System.out.println("Current Ticket Rate -> Economy : "+e_price);
        }
        else {
            System.out.println("Available Seats : Business -> " + b_seat);
            System.out.println("Current Ticket Rate -> Business : " + b_price);
        }
        System.out.print("Number of Seat Booking? ");
        bookNoSeat = scanner.nextInt();
        temp = bookNoSeat;
        if(e_seat != 0 || b_seat !=0){
            if(seat_type.equals("E")) {
                while (temp > 0) {
                    e_seat--;
                    temp--;
                }
                System.out.println(f_type+" Flight --> "+bookNoSeat+" Economy Seat Booked Successfully.");
                amount = bookNoSeat * e_price;
                System.out.println("Payment :"+amount);
                e_price *= 200;
                insertEconomyData(e_seat,e_price,bookNoSeat,amount);
                bookingData(booking_id,f_type,seat_type,p_id);
            }
            else {
                while (temp>0) {
                    b_seat--;
                    temp--;
                }
                booking_id = idGeneration1();
                System.out.println("-----Booking Id-----"+booking_id);
                System.out.println(f_type+" Flight --> "+bookNoSeat+"Business Seat Booked Successfully.");
                int result = bookNoSeat * b_price;
                System.out.println("Payment :"+result);
                b_price *= 100;
                insertBusinessData(b_seat,b_price,bookNoSeat,amount);
                bookingData(booking_id,f_type,seat_type,p_id);
            }
        }
        else {
            System.out.println("Seat's Not Available..Booking Takal ");
            takal(f_type);
        }
    }
    public void takal(int f_type){
       int meals = 200;
        if(t_seat!=0) {
            while (temp > 0) {
                t_seat--;
                temp--;
            }
        }
        else {
            System.out.println("Ticket Not Available");
        }
        booking_id = idGeneration1();
        System.out.println("-----Booking Id-----"+booking_id);
        System.out.println(f_type+" Flight --> "+bookNoSeat+" Takal Seat Booked Successfully.");
        amount = bookNoSeat * t_price * meals;
        System.out.println("Payment :"+amount);
        t_price *= 200;
        insertTakalData(t_seat,t_price,bookNoSeat,amount);
        bookingData(booking_id,f_type,seat_type,p_id);
    }
    public int idGeneration1() {
        Random r=new Random();
        int rn =r.nextInt(10000000);
        return rn;
    }
    private void bookingData(int booking_id, int f_type, String seat_type,int p_id) {
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Booking_Table VALUES("+booking_id+","+f_type+",'"+seat_type+"',"+p_id+")";
            statement.executeUpdate(sql);
            System.out.println("-------------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void insertEconomyData(int e_seat, int e_price, int bookNoSeat, int amount) {
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Economy_Table (No_Seat,E_Price,Book_No_Seat,Amount) VALUES("+e_seat+","+e_price+","+bookNoSeat+","+amount+")";
            statement.executeUpdate(sql);
            System.out.println("-----------------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void insertBusinessData(int b_seat, int b_price, int bookNoSeat, int amount) {
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Business_Table (No_Seat,B_Price,Book_No_Seat,Amount) VALUES("+b_seat+","+b_price+","+bookNoSeat+","+amount+")";
            statement.executeUpdate(sql);
            System.out.println("-----------------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void insertTakalData(int t_seat, int t_price, int bookNoSeat, int amount) {
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "INSERT INTO Takal_Table (No_Seat,T_Price,Book_No_Seat,Amount) VALUES("+t_seat+","+t_price+","+bookNoSeat+","+amount+")";
            statement.executeUpdate(sql);
            System.out.println("-----------------------------------------------");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void cancel(){
        String type="";
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            System.out.print("Enter the Booking Id : ");
            int B_id = scanner.nextInt();
            String sql1 = "SELECT * FROM BookingTable where Booking_Id = '"+B_id+"'";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                type = resultSet.getString("Seat_Type");
                System.out.println("->Number of Booking Cancelled ");
                int c_No = scanner.nextInt();
                if (type.equals("E")) {
                    while (c_No > 0) {
                        e_seat++;
                        c_No--;
                    }
                    balance = c_No * 500;
                    e_price -= balance;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + e_price);
                    updateData(e_seat,e_price,amount,p_id);
                } else if (type.equals("B")) {
                    balance = c_No * 300;
                    while (c_No > 0) {
                        b_seat++;
                        c_No--;
                    }
                    b_price -= balance;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + b_price);
                    updateData(b_seat,b_price,amount,p_id);
                } else {
                    balance = c_No * 600;
                    while (c_No > 0) {
                        t_seat++;
                        c_No--;
                    }
                    t_price -= balance;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + t_price);
                    updateData(t_seat,t_price,amount,p_id);
                }
            }
            System.out.println("Your Ticket Cancelled Successfully...!!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void updateData(int e_seat,int e_price,int amount,int p_id) {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql ="UPDATE Economy_Table SET No_Seat ="+e_seat+",E_Price = "+e_price+",Amount = "+amount+",WHERE P_Id = "+p_id+"";
            statement.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
