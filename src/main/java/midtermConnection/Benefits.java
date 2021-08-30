package midtermConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Benefits extends DatabaseConnection{

	Scanner bits = new Scanner(System.in);
	String b = bits.nextLine();
	String t = "go to main";
	

	public void findBenefits() 
	{
		DatabaseConnection bitsConn = new DatabaseConnection();
		Scanner bits = new Scanner(System.in);
		
		
		try
		{
			String findBenefits = "Select employee.first_name, benefits.* FROM benefits inner join employee on employee.employee_number = benefits.employee_number";
			Statement statement = bitsConn.connection.createStatement();
			ResultSet result1 = statement.executeQuery(findBenefits);
			System.out.println("-----------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %10s %10s", "FIRST NAME", "EMPLOYEE NUMBER", "401K", "HEALTH INSURANCE", "PAID LEAVE","DENTAL INSURANCE");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			
			int count = 0;
			while(result1.next()) {
				String fname = result1.getString("first_name");
			    int employeeNum = result1.getInt("employee_number");
			    boolean fook = result1.getBoolean("FourohONEK");
			    boolean healI = result1.getBoolean("HealthInsurance");
			    boolean paiL = result1.getBoolean("PaidLeave");
			    boolean dentI = result1.getBoolean("DentalInsurance");
			    
			    String output = "|#%d:| %-10s | %-10d | %-10b| %-10b | %-10b | %-10b|";
			    System.out.println(String.format(output, ++count, fname, employeeNum, fook, healI, paiL, dentI));
				
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void addBenefits() 
	{
		DatabaseConnection bitsConn = new DatabaseConnection();
		Scanner bits = new Scanner(System.in);
		
		try
		{
			String addBenefits = "Insert INTO benefits ( FourOhONEK, HealthInsurance, DentalInsurance, PaidLeave, employee_number) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement statement = bitsConn.connection.prepareStatement(addBenefits);
			System.out.println("Please type true for yes and false for no");
			System.out.println("Do you want 401k?");			
			statement.setBoolean(1, bits.nextBoolean());
			System.out.println("Do you want health insurance?");
			statement.setBoolean(2, bits.nextBoolean());
			System.out.println("Do you want dental insurance?");
			statement.setBoolean(3, bits.nextBoolean());
			System.out.println("Do you want paid holidays?");
			statement.setBoolean(4, bits.nextBoolean());
			System.out.println("Enter your employee number");
			statement.setInt(5, bits.nextInt());
			
			int rowsInserted = statement.executeUpdate();
			
			if (rowsInserted > 0) 
			{
				System.out.println("Benefits was added");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteBenefits() {
		DatabaseConnection bitsConn = new DatabaseConnection();
		Scanner bits = new Scanner(System.in);
		try
		{
			String deleteBenefits = "DELETE FROM benefits WHERE employee_number = ?";
			PreparedStatement statement = bitsConn.connection.prepareStatement(deleteBenefits);
			System.out.println("Enter the employee number you want to delete benefits for.");
			statement.setInt(1, bits.nextInt());
			int rowsDeleted = statement.executeUpdate();
			
			if (rowsDeleted > 0) 
			{
				System.out.println("Benefits was deleted");
			}
			findBenefits();
		}		
		catch (Exception e)
		{
			e.printStackTrace();
		}
			
	}
	public void updateBenefits() {
		 DatabaseConnection bitsConn = new DatabaseConnection();
		 Scanner bits = new Scanner(System.in);
		 try {
			 String updateEmployee = "UPDATE benefits SET FourOhONEK = ?, HealthInsurance = ?, DentalInsurance = ?, PaidLeave = ? WHERE employee_number = ?";
			 PreparedStatement statement = bitsConn.connection.prepareStatement(updateEmployee);
			 			 
			 System.out.println("Enter true for yes, false for no.");
			 System.out.println("Do you want to update 401k?");
			 statement.setBoolean(1,bits.nextBoolean());
			 System.out.println("Do you want to update health insurance?");
			 statement.setBoolean(2, bits.nextBoolean());
			 System.out.println("Do you want to update dental insurance?");
			 statement.setBoolean(3, bits.nextBoolean());
			 System.out.println("Do you want to update paid holidays?");
			 statement.setBoolean(4, bits.nextBoolean());
			 System.out.println("Enter your empoloyee number for update");
			 statement.setInt(5, bits.nextInt());
			 int rowsUpdated = statement.executeUpdate();
			 
			 if(rowsUpdated > 0) {
				 System .out.println("Benefits has been updated");
			 }
			 
			 findBenefits();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
}
