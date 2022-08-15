import java.util.*;

public class MainFunction {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("----***---Ticket Booking---***-----");
        reg();
    }
    public static void reg(){
        Register r = new Register();
        System.out.println("1)Register \t 2)Login");
        int op = scanner.nextInt();
        switch (op) {
            case 1:
                r.register();
                bookTicket();
                break;
            case 2:
                r.login();
                System.out.println(" >>>>>>>>>Choose your choice<<<<<<<<<<< ");
                bookTicket();
                break;
            default:
                System.out.println("Enter Valid Category :");
                reg();
                break;
        }
    }
    private static void bookTicket() {
        System.out.println(" 1)Booking Ticket \n 2)Cancel Ticket \n 3)Logout ");
        int option = scanner.nextInt();
        BookingTicket b = new BookingTicket();
        switch (option){
            case 1:
                b.book();
                bookTicket();
                break;
            case 2:
                b.cancel();
                bookTicket();
                break;
            case 3:
                System.out.println("---ThankYou---!!!");
                System.exit(0);
            default:
                System.out.println("Enter valid category....");
                bookTicket();
                break;
        }
    }
}

