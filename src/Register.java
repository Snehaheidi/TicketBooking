import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Register {
    Scanner scanner = new Scanner(System.in);
    String DB_URL = "jdbc:mysql://localhost/TicketBooking";
    final String USER = "root";
    final String PASS = "root";
    public void register(){
        System.out.print("Name : ");
        String name = scanner.nextLine();
        System.out.print("age : ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Set Password : ");
        String pass = scanner.nextLine();
        int p_id = idGeneration();
        insert(p_id,name,age,pass);
    }
    public int  idGeneration() {
        Random r=new Random();
        int rn=r.nextInt(1000000);
        return rn;
    }
    public void insert(int p_id,String name ,int age,String pass){
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
    public void login(){
        System.out.print("Enter your Passenger ID : ");
        int p_id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Name :");
        String name = scanner.nextLine();
        System.out.print("Enter your Password : ");
        String pass = scanner.nextLine();
        System.out.println("");
        displayDetails(p_id,name,pass);
    }
    public void displayDetails(int p_id,String name,String pass){
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql1 = "SELECT * FROM PASSENGER_Table1 where P_Id = '"+p_id+"'";
            ResultSet resultSet = statement.executeQuery(sql1);
            String sql2 = "SELECT * FROM Booking_Table where P_Id ='"+p_id+"'";
            ResultSet resultSet1 = statement.executeQuery(sql2);
            while (resultSet.next()) {
                String pName = resultSet.getString("P_Name");
                String word = resultSet.getString("Password");
                if(pName.equals(name) && word.equals(pass)){
                    System.out.println("We Welcome you,"+resultSet.getString("P_Name"));
                }
                else {
                    System.out.println("Your name or password is incorrect :-(");
                    login();
                }
            }
            while(resultSet1.next()){
                String type = resultSet1.getString("Seat_Type");
                if(type.equals("E")){
                    String sql3 = "SELECT * FROM Economy_Table where P_Id ='"+p_id+"'";
                    ResultSet resultSet3 = statement.executeQuery(sql3);
                    System.out.println("Current Ticket Rate : "+resultSet3.getString("E_Price"));
                }
                else if (type.equals("B")) {
                    String s = "SELECT * FROM Business_Table where P_Id ='"+p_id+"'";
                    ResultSet resultSet4 = statement.executeQuery(s);
                    System.out.println("Current Ticket Rate : "+resultSet4.getString("B_Price"));
                }
                else{
                    String sq = "SELECT * FROM Takal_Table where P_Id ='"+p_id+"'";
                    ResultSet resultSet5 = statement.executeQuery(sq);
                    System.out.println("Current Ticket Rate : "+resultSet5.getString("T_Price"));
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
