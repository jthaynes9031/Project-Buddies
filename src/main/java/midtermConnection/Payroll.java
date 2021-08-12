package midtermConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Payroll extends Employee {
	DatabaseConnection databaseConn = new DatabaseConnection();
	Employee emp = new Employee();
	Scanner input = new Scanner(System.in);
	public double payrate;
	

	//set pay rate
	public void setPayrate(){
		//add pay rate to specific employee
		
		try {
			
			String payRate = "INSERT INTO payroll (pay_rate, employee_number) values (?, ?)";
			PreparedStatement statement = databaseConn.connection.prepareStatement(payRate);
			System.out.println("Choose the hourly rate?");
			statement.setString(1, myObj.nextLine());
			System.out.println("Enter the employee number");
			statement.setString(2, myObj.nextLine());
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted > 0) {
				System.out.println("pay rate is set");
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("");
	}

	//retrieve employee pay rate
	public void findPayrate() {
		//find employee pay rate
		try
		{
			String findPayRate = "Select pay_rate from payroll where employee_number = ?";
			PreparedStatement statement = databaseConn.connection.prepareStatement(findPayRate);
			System.out.println("What is the employee number for the pay you would like to view?");
			statement.setInt(1, myObj.nextInt());
			ResultSet result1 = statement.executeQuery();
			
			
			result1.next();
				payrate = result1.getDouble("pay_rate");
				System.out.println(payrate);

		 	 
		}catch(Exception e){
		 e.printStackTrace();
		}
	}
	//net pay
	public void netPay() {
		
		//weekly pay array
		//benefits
		
	}
	
	// Update Pay
	public void updatePay() {
		DatabaseConnection bitsConn = new DatabaseConnection();
		Scanner bits = new Scanner(System.in);
		try {
		 String updatePay = "UPDATE payroll SET pay_rate = ?, direct_deposit = ? WHERE employee_number = ?";
		 PreparedStatement statement = bitsConn.connection.prepareStatement(updatePay);
		 			 
		
		 System.out.println("What do we set pay rate to?");
		 statement.setDouble(1, bits.nextDouble());
		 System.out.println("Do you want direct deposit?");
		 statement.setBoolean(2, bits.nextBoolean());
		 System.out.println("Enter your empoloyee number for update");
		 statement.setInt(3, bits.nextInt());
		 int rowsUpdated = statement.executeUpdate();
		 
		 if(rowsUpdated > 0) {
			 System .out.println("Benefits has been updated");
		 }
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 bits.close();// ability to update pay rate
	}

	//delete Pay
	public void deletePay() {
		
		try
		{
		String deletePay = " DELETE FROM payroll WHERE employee_number = ?";
		 PreparedStatement statement = databaseConn.connection.prepareStatement(deletePay);
		 System.out.println("What is the id of the employee whose pay you would like to delete");
		 statement.setString(1, myObj.nextLine());
		 int rowsDeleted = statement.executeUpdate();
		 
		 if(rowsDeleted > 0) {
			 System.out.println("Pay rate has been deleted");
		 }
		
			 
		}catch(Exception e){
		 e.printStackTrace();
		}
	}
	//direct deposit boolean
	public void deposit() {
		
		try
		{
		String changeDeposit = "UPDATE payroll SET direct_deposit = ? WHERE employee_number = ?";
		PreparedStatement statement = databaseConn.connection.prepareStatement(changeDeposit);
		
		System.out.println("Do you want direct deposit?");
		statement.setBoolean(1, input.nextBoolean());
		System.out.println("type employee number");
		statement.setInt(2, input.nextInt());
		int rowsUpdated = statement.executeUpdate();
		 
		 if(rowsUpdated > 0) {
			 System .out.println("direct deposit has been changed");
		 }
		}catch(Exception e) {
			 e.printStackTrace();
		 }
		 input.close();
	}
	
	//gross Pay
	public void grossPay() {
		// the net pay
		//benefits
	}
	
	public void findPayAll() {
		try {
			String sql = "Select first_name, payroll.* from payroll inner join employee on employee.employee_number = payroll.employee_number;";
			 
			Statement statement = databaseConn.connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			 
			int count = 0;
			 
			System.out.println("-----------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %10s %10s", "COUNT", "FIRST NAME", "EMPLOYEE NUMBER", "PAYRATE", "OVERTIME", "DIRECT DEPOSIT");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			while (result.next()){
				String fname = result.getString("first_name");
			    int employeeNum = result.getInt("employee_number");
			    double payRate = result.getDouble("pay_rate");
			    boolean overtime = result.getBoolean("overtime");
			    boolean direct = result.getBoolean("direct_deposit");
			 
			    String output = "|#%d:| %-10s | %-10d | %-10f | %-10b | %-10b";
			    System.out.println(String.format(output, ++count, fname, employeeNum, payRate, overtime, direct));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
