package midtermConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;




public class DatabaseConnection{
	
	
	
	Scanner input = new Scanner(System.in);
	Connection connection = null;
	
	
	// Constructor (connector method below main method )
	public DatabaseConnection() {

		String url = "jdbc:mysql://localhost:3306/final";
		String user = "root";
		String password = "Password1";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	
	//EVERYTHING RUNS ON HERE!!!
	public static void main(String[] args) {
		
		System.out.println("--------------------------------");
		System.out.println("Welcome to the HRIS, instructions on things you can do are below");
		System.out.println("--------------------------------");
		System.out.println("This HRIS allows you to create, read, update, and delete data in the database pertaining to employees or yourself");
		System.out.println("--------------------------------");
		System.out.println("Start by pressing enter,  Enjoy :)");
		Employee emp = new Employee();
		Prompt prom = new Prompt();
		
		connector();
		
		System.out.println("Please clock in :)");
	
			emp.employeeIn();

	}
	//connector method
	public static void connector() {
		Scanner input = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/final";
		String user = "root";
		String password = "Password1";
		
		//Try statement to connect to database
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
				DriverManager.getConnection(url, user, password);
				    System.out.println("Welcome");
				    //System.out.println("what would you like to do");
				    //input.nextLine();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
	}catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	
}
