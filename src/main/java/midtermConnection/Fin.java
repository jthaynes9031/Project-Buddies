package midtermConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Fin {
	DatabaseConnection databaseConn = new DatabaseConnection();
	Scanner input = new Scanner(System.in);
	
	/*
	 this class is for performance,training and positions 
	*/
	
	public void addPosition() {
		
		try {
			
			String posi = "Insert Into positions (administer, employee, employee_number) values(?,?,?)";
			PreparedStatement statement = databaseConn.connection.prepareStatement(posi);
			
			System.out.println("is employee a manager if not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("if you typed false on the previous question type 'true' here");
			statement.setBoolean(2, input.nextBoolean());
			System.out.println("type employee number you would like to apply position to");
			statement.setInt(3,input.nextInt());
			
			int rowsInserted = statement.executeUpdate();
			 
			if(rowsInserted > 0 ) {
				System.out.println("Employee is in Training");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addTraining() {
		
		try {
			
			String train = "Insert into training (isTraining,employee_number) values(?,?)";
			PreparedStatement statement = databaseConn.connection.prepareStatement(train);
			
			System.out.println("is employee training? if so type true is not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("insert employee number of who is training");
			statement.setInt(2, input.nextInt());
			
			int rowsInserted = statement.executeUpdate();
			 
			if(rowsInserted > 0 ) {
				System.out.println("New user was added");
			}
			
			findEmpInTrain();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTraining() {
	
		try {
			
			String uTrain = "Update training set isTraining = ? where employee_number = ?";
			PreparedStatement statement = databaseConn.connection.prepareStatement(uTrain);
			System.out.println("is employee training type true if not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("type employee number");
			statement.setInt(2, input.nextInt());
			
			int rowsInserted = statement.executeUpdate();
			 
			if(rowsInserted > 0 ) {
				System.out.println("Training was updated");
			}
			
			findEmpInTrain();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePosition() {
		
		try {
			
			String uPosi = "Update positions set administer = ? employee = ? where employee_number = ?";
			PreparedStatement statement = databaseConn.connection.prepareStatement(uPosi);
			System.out.println("is employee a manager");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("is employee just an employee");
			statement.setBoolean(2, input.nextBoolean());
			System.out.println("type employee number");
			statement.setInt(3, input.nextInt());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void findEmpInTrain() {
		try {
			
			String findTrain = "Select first_name, training.* from training inner join employee on employee.employee_number = training.employee_number where istraining = 1";
			Statement statement = databaseConn.connection.createStatement();
			ResultSet result = statement.executeQuery(findTrain);
			 
			int count = 0;
			System.out.println("-----------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s", "COUNT", "FIRST NAME", "EMPLOYEE NUMBER", "TRAINING");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			while (result.next()){
				String fname = result.getString("first_name");
			    int employeeNum = result.getInt("employee_number");
			    boolean train = result.getBoolean("isTraining");
			 
			    String output = "|#%d:| %-10s | %-10d | %-10b";
			    System.out.println(String.format(output, ++count, fname, employeeNum, train));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPerformance() {
		
		try {
			String Sql = "Insert into performance (employee_num, performance) values(?,?)";
			PreparedStatement statement = databaseConn.connection.prepareStatement(Sql);
			
			System.out.println("type the employee number we're giving a performance review for");
			statement.setInt(1, input.nextInt());
			System.out.println("insert number between 1 - 5, 1 meaning poor, 5 meaning excellent");
			statement.setInt(2, input.nextInt());
			
			
			int rowsInserted = statement.executeUpdate();
			 
			if(rowsInserted > 0 ) {
				System.out.println("Performance was add to user");
			}
			
			performanceTable();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void performanceTable() {
		
		try {
			
			String findPerf = "Select first_name, last_name, performance.* from performance inner join employee on employee.employee_number = performance.employee_number";
			Statement statement = databaseConn.connection.createStatement();
			ResultSet result = statement.executeQuery(findPerf);
			 
			int count = 0;
			System.out.println("-----------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %10s", "COUNT", "FIRST NAME", "LAST NAME", "EMPLOYEE NUMBER", "PERFORMANCE");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			while (result.next()){
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
			    int employeeNum = result.getInt("employee_number");
			    int performance = result.getInt("performance");
			 
			    String output = "|#%d:| %-10s | %-10s | %-10d | %-10d";
			    System.out.println(String.format(output, ++count, fname, lname, employeeNum, performance));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public void positionTable() {
		try {
			
			String findPerf = "Select first_name, last_name, performance.* from performance inner join employee on employee.employee_number = performance.employee_number";
			Statement statement = databaseConn.connection.createStatement();
			ResultSet result = statement.executeQuery(findPerf);
			 
			int count = 0;
			System.out.println("-----------------------------------------------------------------------------");
			System.out.printf("%10s %10s %10s %10s %10s %10S", "COUNT", "FIRST NAME", "LAST NAME", "EMPLOYEE NUMBER", "MANAGER", "EMPLOYEE");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			while (result.next()){
				String fname = result.getString("first_name");
				String lname = result.getString("last_name");
			    int employeeNum = result.getInt("employee_number");
			    boolean admin  = result.getBoolean("administer");
			    boolean emp = result.getBoolean("employee");
			 
			    String output = "|#%d:| %-10s | %-10s | %-10d | %-10b | %-10b|";
			    System.out.println(String.format(output, ++count, fname, lname, employeeNum, admin, emp));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
