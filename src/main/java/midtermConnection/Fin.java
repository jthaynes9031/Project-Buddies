package midtermConnection;

public class Fin {
	
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
