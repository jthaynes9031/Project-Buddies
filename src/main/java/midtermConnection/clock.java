package midtermConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class clock {
	/*
	 * global variables
	 */
	DatabaseConnection databaseConn = new DatabaseConnection();
	Scanner myObj = new Scanner(System.in);
	LocalDateTime mew;
	LocalDateTime mewtwo;
	int employeeNuNu;
	
	
	
	 /*
	  * THESE ARE ALL METHODS FOR OUR TIMING FUNCTION
	  * THE MAIN SOURCE OF WHAT MAKES OUR PROGRAM SMOOTH
	  * AND PRETTY COOL
	  */
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
		 try {			 
			 String attendance = "SELECT attendance, administer, positions.employee, employee.employee_number FROM employee inner join positions on positions.employee_number = employee.employee_number where employee.employee_number = ?";
			 PreparedStatement statement1 = databaseConn.connection.prepareStatement(attendance); 
			 System.out.println("Confirm employee number");
			 statement1.setInt(1,myObj.nextInt());
			 ResultSet rs = statement1.executeQuery();
			 
			 
			 rs.next();
				 int inOrOut = rs.getInt("attendance");
				 int man = rs.getInt("administer");
				 int em = rs.getInt("employee");
				 employeeNuNu = rs.getInt("employee_number");
				 
				 if(inOrOut == 1) {
					 System.out.println("You're In");
					 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");  
					 mew = LocalDateTime.now();
					 System.out.println(dtf.format(mew));
					 System.out.println("=======================THANK YOU FOR CLOCKING IN PRESS ENTER " + employeeNuNu + "==========================");
					 if(man == 1 & em == 0) {
						 prom.promptM();
					 }
					 else if (man == 0 && em == 1) {
						 prom.PromptA();
					 }
					 else {
						 System.out.println("employee doesnt have a position");
					 }
					 
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
