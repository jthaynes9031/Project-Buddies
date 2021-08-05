package midtermConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class notAttached {
	DatabaseConnection databaseConn = new DatabaseConnection();
	Scanner myObj = new Scanner(System.in);
	
	/*
	public String findEmployee() {
		
		try {
			String findEmployee = "SELECT * FROM employee";
			PreparedStatement statement = databaseConn.connection.prepareStatement(findEmployee);
			ResultSet result1 = statement.executeQuery();
			
			
			String email = result1.getString("email");
			integer employeeNum = result1.getInt("employee_Number");
			String fN = result1.getString("first_name");
			String lN = result1.getString("last_name");
			String address = result1.getString("address");
			boolean attendance = result1.getBoolean("attendance");
			
			String tab = String.format("| %10s | %10s | %10s| %10s | Are they here?: %b| Employee ID: %04d |", 
					fN,lN,address,email,attendance,employeeNum);
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	*/
	
}
