import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.sql.*;
import java.util.Scanner;

public class CancelTicket {
    static String DB_URL = "jdbc:mysql://localhost/TicketBooking";
    static final String USER = "root";
    static final String PASS = "root";
    public static void cancel(){
        Scanner scanner = new Scanner(System.in);
        String type;
        int e_seat = 10,b_seat = 15, e_price = 5500 , b_price = 5000 , t_price = 5500 , t_seat = 5;
        String seat_type ="";
        int bookNoSeat , temp , amount = 0,balance ,p_id;
        System.out.print("Enter the Passenger Id : ");
        int B_id = scanner.nextInt();
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql1 = "SELECT * FROM Book_Table where P_Id = '"+B_id+"'";
            ResultSet resultSet = statement.executeQuery(sql1);
            while (resultSet.next()) {
                type = resultSet.getString("Seat_Type");
                System.out.println("-> Number of Booking Cancelled ");
                int c_No = scanner.nextInt();
                if (type.equals("E")) {
                    while (c_No > 0) {
                        e_seat++;
                        c_No--;
                    }
                    balance = c_No * 100;
                    e_price *= 500;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + e_price);
                    InsertUpdateData.updateData(type,e_seat,e_price,amount,B_id);
                } else if (type.equals("B")) {
                    balance = c_No * 300;
                    while (c_No > 0) {
                        b_seat++;
                        c_No--;
                    }
                    b_price += 200;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + b_price);
                    System.out.println("Your Refund Amount = "+amount);
                    InsertUpdateData.updateData(type,b_seat,b_price,amount,B_id);
                } else {
                    balance = c_No * 600;
                    while (c_No > 0) {
                        t_seat++;
                        c_No--;
                    }
                    t_price -= balance;
                    amount -= balance;
                    System.out.println("Current Ticket Price : " + t_price);
                    InsertUpdateData.updateData(type,t_seat,t_price,amount,B_id);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
