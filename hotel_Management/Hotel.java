package hotel_Management;
import java.sql.*;
import java.util.Scanner;
public class Hotel {

	public static void main(String[] args) throws ClassNotFoundException{
		try {
			Scanner s=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/nams","root","root");
			Statement st=c.createStatement();
			ResultSet r;
			boolean b=true;
			while(b) {
				System.out.println("Enter 1 to book rooms");
				System.out.println("Enter 2 to display customer details");
				System.out.println("Enter 3 to update the customer table");
				System.out.println("Enter 4 to exit");
				String a=s.nextLine();
				switch(a) {
				case "1":
					System.out.println("Enter the name :");
					String name=s.nextLine();
					System.out.println("Enter the id : ");
					String id=s.nextLine();
					System.out.println("Enter the phone number :");
					String phone_no=s.nextLine();
					System.out.println("Enter the room_no :");
					String r_no=s.nextLine();
					System.out.println("Enter the address :");
					String address=s.nextLine();
					st.executeUpdate("Insert into customer values('"+name+"',"+id+",'"+phone_no+"',"+r_no+",'"+address+"')");
					st.executeUpdate("update rooms set status='BOOKED' where room_no="+r_no);
					break;
				case "2" :
					System.out.println("Enter 1 to display all customer details :");
					System.out.println("Enter 2 to display particular customer details");
					String y1=s.nextLine();
					if(y1.equals("1")) {
					r=st.executeQuery("Select * from customer");
					while(r.next()) {
						System.out.println("Name : "+r.getString(1));
						System.out.println("Id : "+r.getInt(2));
						System.out.println("Phone_Number : "+r.getString(3));
						System.out.println("Room_No : "+r.getInt(4));
						System.out.println("Address : "+r.getString(5));
					}}
					else if(y1.equals("2")) {
						System.out.println("Enter the room number :");
						String y2=s.nextLine();
						r=st.executeQuery("Select * from customer where room_no="+y2);
						while(r.next()) {
							System.out.println("Name : "+r.getString(1));
							System.out.println("Id : "+r.getInt(2));
							System.out.println("Phone_Number : "+r.getString(3));
							System.out.println("Room_No : "+r.getInt(4));
							System.out.println("Address : "+r.getString(5));
						}
					}
					else
						System.out.println("Enter valid input !");
					break;
				case "3":
					System.out.println("Enter the room number to be updated");
					int rno=Integer.parseInt(s.nextLine());
					st.executeUpdate("Update rooms set status='NOT BOOKED' where room_no="+rno);
					st.executeUpdate("Delete from customer where room_no="+rno);
					break;
				case "4":
					b=false;
					break;
				default :System.out.println("Enter valid option !");
				}
			}
			s.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
