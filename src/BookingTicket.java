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
    int bookNoSeat , temp , amount ,balance ,p_id;
    public void book(){
        System.out.println("Enter the Passenger Id");
        p_id = scanner.nextInt();
        System.out.println("Available Flights : \n *101 (Madurai to Chennai) \n *102 (Mumbai to Chennai)");
        int f_type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("* Economy class   * Business class (enter E/B)");
        seat_type = scanner.nextLine();
        int booking_id = idGeneration1();
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
                System.out.println("-----Booking Id-----"+booking_id);
                System.out.println(f_type+" Flight --> "+bookNoSeat+" Economy Seat Booked Successfully.");
                amount = bookNoSeat * e_price;
                System.out.println("Payment :"+amount);
                e_price *= 200;
                InsertUpdateData.insertData(seat_type,p_id,e_seat,e_price,bookNoSeat,amount);
                InsertUpdateData.bookingData(booking_id,f_type,seat_type,p_id);
            }
            else {
                while (temp>0) {
                    b_seat--;
                    temp--;
                }
                System.out.println("-----Booking Id-----"+booking_id);
                System.out.println(f_type+" Flight --> "+bookNoSeat+"Business Seat Booked Successfully.");
                int result = bookNoSeat * b_price;
                System.out.println("Payment :"+result);
                b_price *= 100;
                InsertUpdateData.bookingData(booking_id,f_type,seat_type,p_id);
                InsertUpdateData.insertData(seat_type,p_id,b_seat,b_price,bookNoSeat,amount);
            }
        }
        else {
            System.out.println("Seat's Not Available..Booking Takal ");
            takal(f_type,bookNoSeat);
        }
    }
    public void takal(int f_type,int bookNoSeat){
       int meals = 200;
       temp = bookNoSeat;
        if(t_seat!=0) {
            while (temp > 0) {
                t_seat--;
                temp--;
            }
        }
        else {
            System.out.println("Ticket Not Available");
        }
        int booking_id = idGeneration1();
        System.out.println("-----Booking Id-----"+booking_id);
        System.out.println(f_type+" Flight --> "+bookNoSeat+" Takal Seat Booked Successfully.");
        amount = bookNoSeat * t_price * meals;
        System.out.println("Payment :"+amount);
        t_price *= 200;
        InsertUpdateData.insertData(seat_type,p_id,t_seat,t_price,bookNoSeat,amount);
        InsertUpdateData.bookingData(booking_id,f_type,seat_type,p_id);
    }
    public int idGeneration1() {
        Random r=new Random();
         return r.nextInt(10000000);
    }
}
