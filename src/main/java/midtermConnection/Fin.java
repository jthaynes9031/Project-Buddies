package midtermConnection;

public class Fin {
	
	/*
	 this class is for performance,training and positions
	 using getters and setters
	 and will implement in the interface
	 
	*/
	int employeeId;
	int performance;
	
	
	public Fin(int id, int performance) {
		this.employeeId = id;
		this.performance = performance;
	}
	
	
	public void setEmployeePerformance(int performance) {
		this.performance = performance;
		
	}
	
	public int getEmployeePerformance() {
		return this.performance;
	}
}
