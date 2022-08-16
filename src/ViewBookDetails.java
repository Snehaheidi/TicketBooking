import java.sql.*;
import java.util.Scanner;

public class ViewBookDetails {
    public static void bookDetails(){
        String DB_URL = "jdbc:mysql://localhost/TicketBooking";
        final String USER = "root";
        final String PASS = "root";
        Scanner scanner = new Scanner(System.in);
        String type = "";
        System.out.println("Enter the Passenger ID :");
        int id = scanner.nextInt();
        try{
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement statement = con.createStatement();
            String sql = "Select * from Book_Table where P_Id = "+id+"";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("*****BOOKING DETAILS******");
                System.out.println("Flight Type : "+resultSet.getInt("F_Type"));
                type = resultSet.getString("Seat_Type");
                System.out.println("Seat Type (E -> Economy ,B -> Business , T-> Takal ) :  "+type);
            }
            if(type.equals("E")){
                String sql1 = "Select * from Economy_Table where P_Id = "+id+"";
                ResultSet resultSet1 = statement.executeQuery(sql1);
                while(resultSet1.next()){
                    System.out.println("Booked Seat : "+resultSet1.getInt("Book_No_Seat"));
                    System.out.println("Total Payment :"+resultSet1.getInt("Amount"));
                }
            }
            else if(type.equals("B")){
                String sql1 = "Select * from Business_Table where P_Id = "+id+"";
                ResultSet resultSet1 = statement.executeQuery(sql1);
                while(resultSet1.next()){
                    System.out.println("Booked Seat : "+resultSet1.getInt("Book_No_Seat"));
                    System.out.println("Total Payment :"+resultSet1.getInt("Amount"));
                }
            }
            else {
                String sql1 = "Select * from Takal_Table where P_Id = "+id+"";
                ResultSet resultSet1 = statement.executeQuery(sql1);
                while(resultSet1.next()){
                    System.out.println("Booked Seat : "+resultSet1.getInt("Book_No_Seat"));
                    System.out.println("Total Payment :"+resultSet1.getInt("Amount"));
                }
            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
