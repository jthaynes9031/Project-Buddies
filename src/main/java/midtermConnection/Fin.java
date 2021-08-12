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
		String posi = "Insert Into positions (administer, employee, employee_number) values(?,?,?)";
		
		try {
			
			PreparedStatement statement = databaseConn.connection.prepareStatement(posi);
			
			System.out.println("is employee a manager if not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("if you typed false on the previous question type 'true' here");
			statement.setBoolean(2, input.nextBoolean());
			System.out.println("type employee number you would like to apply position to");
			statement.setInt(4,input.nextInt());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addTraining() {
		String train = "Insert into training (isTraining, employee_number) values(?,?)";
		
		try {
			
			PreparedStatement statement = databaseConn.connection.prepareStatement(train);
			
			System.out.println("is employee training? if so type true is not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("insert employee number of who is training");
			statement.setInt(2, input.nextInt());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTraining() {
		String uTrain = "Update training set isTraining = ? where employee_number = ?";
	
		try {
			
			PreparedStatement statement = databaseConn.connection.prepareStatement(uTrain);
			System.out.println("is employee training type true if not type false");
			statement.setBoolean(1, input.nextBoolean());
			System.out.println("type employee number");
			statement.setInt(2, input.nextInt());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePosition() {
		String uPosi = "Update positions set administer = ? employee = ? where employee_number = ?";
		
		try {
			
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
			 
			while (result.next()){
				String fname = result.getString("first_name");
			    int employeeNum = result.getInt("employee_number");
			    boolean train = result.getBoolean("isTraining");
			 
			    String output = "|#%d: Employee firstname:  %-10s |  Employee number: %-10d | %-10b";
			    System.out.println(String.format(output, ++count, fname, employeeNum, train));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addPerformance() {
		String Sql = "Insert into performance (employee_num, performance) values(?,?)";
		
		try {
			PreparedStatement statement = databaseConn.connection.prepareStatement(Sql);
			
			System.out.println("type the employee number we're giving a performance review for");
			statement.setInt(1, input.nextInt());
			System.out.println("insert number between 1 - 5, 1 meaning poor, 5 meaning excellent");
			statement.setInt(2, input.nextInt());
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
