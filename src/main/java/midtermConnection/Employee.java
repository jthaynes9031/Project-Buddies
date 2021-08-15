package midtermConnection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;


public class Employee{
	
	
	DatabaseConnection databaseConn = new DatabaseConnection();
	Scanner myObj = new Scanner(System.in);
	LocalDateTime mew;
	LocalDateTime mewtwo;


	
	public void findEmployee() { 
		try 
		{
		
		String findEmployee = "SELECT * FROM employee";
		PreparedStatement statement = databaseConn.connection.prepareStatement(findEmployee);
		ResultSet result1 = statement.executeQuery();
		
		int count = 0;
		
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%5s %15s %10s %10s %10s %10s", "COUNT", "FIRST NAME", "LAST NAME", "EMPLOYEE NUMBER", "EMAIL", "CLOCKED IN");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		while(result1.next()) {
			
			String fname = result1.getString("first_name");
			String lname = result1.getString("last_name");
		    int employeeNum = result1.getInt("employee_number");
		    String email = result1.getString("email");
		    boolean att = result1.getBoolean("attendance");
		    
		    String output = "|#%d: | %10s | %10s | %10d | %10s | %10b |";
		    System.out.println(String.format(output, ++count, fname, lname, employeeNum, email, att));
		}
		
		}catch (Exception e) {
			e.printStackTrace();			
	}	
}
	
	public void addEmployee() {
		 try 
			{			
		    String addEmployee = "INSERT INTO employee (first_name, last_name, email, employee_number) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = databaseConn.connection.prepareStatement(addEmployee);
			
			System.out.println("add firstname");
			statement.setString(1, myObj.next());
			System.out.println("add lastname");
			statement.setString(2, myObj.next());
			System.out.println("add email");
			statement.setString(3, myObj.next());
			System.out.println("add 4 employee number");
			statement.setInt(4,myObj.nextInt());
			
			
				
			int rowsInserted = statement.executeUpdate();
			 
			if(rowsInserted > 0 ) {
				System.out.println("New user was added");
			}
			
			 findEmployee();
			}catch (Exception e){
				e.printStackTrace();
				
		}
	}
	 
	 
	 public void deleteEmployee() {
		 try {
			 String deleteEmployee = " DELETE FROM employee WHERE first_name = ?";
			 PreparedStatement statement = databaseConn.connection.prepareStatement(deleteEmployee);
			 System.out.println("What is the first name of the employee that you would like to delete");
			 statement.setString(1, myObj.nextLine());
			 int rowsDeleted = statement.executeUpdate();
			 
			 if(rowsDeleted > 0) {
				 System.out.println("User has been deleted");
			 }
			 
			 findEmployee();
		 }catch(Exception e){
			 e.printStackTrace();
		
		 }
	 }
	 public void updateEmployee() {
		 try {
			String updateEmployee = "UPDATE employee SET first_name = ?, last_name = ?, email = ? WHERE employee_number = ?";
			 PreparedStatement statement = databaseConn.connection.prepareStatement(updateEmployee);
			 			 
			 //System.out.println("Follow prompt below: ");
			// myObj.next();
			 System.out.println("Keep first name or update first name to: ");
			 statement.setString(1, myObj.next());
			 System.out.println("Keep last name or update  last name to: ");
			 statement.setString(2, myObj.next());
			 System.out.println("Keep email or update email to:");
			 statement.setString(3, myObj.next());
			 System.out.println("Enter employee number for update");
			 statement.setInt(4, myObj.nextInt());
			 int rowsUpdated = statement.executeUpdate();
			 
			 if(rowsUpdated > 0) {
				 System.out.println("User has been updated");
			 }
			 
			 findEmployee();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	 
	 //specific SQL Queries
	 public void employeeIn() {
		 
		 try
		 {
			 String employeeClockIn = "UPDATE employee SET attendance = ?, clockinTime = CURRENT_TIMESTAMP WHERE employee_number = ? ";
			 PreparedStatement statement = databaseConn.connection.prepareStatement(employeeClockIn);
			 System.out.println("clocking in type '1' clocking out type '0' ");
			 statement.setInt(1, myObj.nextInt());
			 System.out.println("enter employee number");
			 statement.setInt(2,myObj.nextInt());
			 int rowsUpdated = statement.executeUpdate();
			 
			 if(rowsUpdated > 0) {
				 attCheck();				
			 }
			 
		 }catch(Exception e){
			 System.out.println("employee not found");
		 }
	 }
	 
	 public void employeeOut(){
		 try
		 {
			 String employeeClockOut = "UPDATE employee SET attendance = 0, clockoutTime = CURRENT_TIMESTAMP WHERE employee_number = ? ";
			 PreparedStatement statement = databaseConn.connection.prepareStatement(employeeClockOut);
			 System.out.println("enter employee number");
			 statement.setInt(1,myObj.nextInt());
			 int rowsUpdated = statement.executeUpdate();
			 
			 if(rowsUpdated > 0) {
				 attCheck();				
			 }
			 
		 }catch(Exception e) {
			 System.out.println("employee not found");
		 }
	 }
	 
		 public void attCheck() {
		Prompt prom = new Prompt();
		 String attendance = "SELECT * FROM employee WHERE employee_number = ? ";
		 try {
			 
			 PreparedStatement statement1 = databaseConn.connection.prepareStatement(attendance);
			 System.out.println("Confirm employee number");
			 statement1.setInt(1,myObj.nextInt());
			 ResultSet rs = statement1.executeQuery();
			 
			 
			 rs.next();
				 int inOrOut = rs.getInt("attendance");
				 
				 if(inOrOut == 1) {
					 System.out.println("You're In");
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
					 mew = LocalDateTime.now();
					 System.out.println(dtf.format(mew));
					 System.out.println("=======================THANK YOU FOR CLOCKING IN PRESS ENTER==========================");
					 prom.promptM();
					 
				 }
				 if(inOrOut == 0) {
					 System.out.println("You're out");
					 DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm:ss");  
					 mewtwo = LocalDateTime.now();
					 System.out.println(dt.format(mewtwo));
					 dTime();					 
				 }
				 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	 }

		 public void dTime() {
			 long hours = ChronoUnit.HOURS.between(mew, mewtwo);
			 long minutes = ChronoUnit.MINUTES.between(mew,mewtwo);
			 long seconds = ChronoUnit.SECONDS.between(mew,mewtwo);
			 System.out.println(hours + " hours");
			 System.out.println(minutes + " minutes");
			 System.out.println((seconds + 1) + " seconds");

		 }


}
