package midtermConnection;

public interface TrainingandPerformance {
	
	/*
	 * declare sql statements as strings 
	 * IMPORTANT change connections from employee_id to employee_number in sql
	 * 
	 */
	
	String SET_PERFORMANCE = "Insert into performance (performance, employee_id) value(?,?)";
	String UPDATE_PERFORMANCE = "Update performance set performance = ? where employee_id = ?";
}
