package midtermConnection;

import java.util.Scanner;

public class Prompt {

	
	public void promptM() {
		Employee emp = new Employee();
		Payroll pay = new Payroll();
		Benefits ben = new Benefits();
		Fin fin = new Fin();
		Scanner input = new Scanner(System.in);
		
		
		String divider = "=====================================================================================================";
		
		boolean quit = false;
		while(!quit) {
			System.out.println("type instructions if youre new");
			String choose = input.nextLine();
			
			
			if(choose.equals("instructions")) {	
				System.out.println(divider);
				System.out.println("type 'add' to insert into database");
				System.out.println("type 'find' to search database");
				System.out.println("type 'update' to update items in database"); 
				System.out.println("type 'delete' to delete items in database");
				System.out.println(divider);
			}

			
			else if(choose.equals("add")) {
				System.out.println("Type either one of these commands");
				System.out.println(divider);
				System.out.println("'add employee'");
				System.out.println("'add payroll'");
				System.out.println("'add benefits'");
				System.out.println("'add performance'");
				System.out.println("'add training'");
				System.out.println("'add position'");
				System.out.println(divider);
			}
			else if(choose.equals("find")) {
				System.out.println("type either one of these commands");
				System.out.println(divider);
				System.out.println("find employee");
				System.out.println("find pay info");
				System.out.println("find benefits");
				System.out.println("employees in training");
				System.out.println(divider);
			}
			else if(choose.equals("delete")) {
				System.out.println("type either one of these commands");
				System.out.println(divider);
				System.out.println("delete employee");
				System.out.println("delete pay info");
				System.out.println("delete benefits");
				System.out.println(divider);
			}
			else if(choose.equals("update")) {
				System.out.println("type either one of these commands");
				System.out.println(divider);
				System.out.println("update employee");
				System.out.println("update pay info");
				System.out.println("update benefits");
				System.out.println("update performance");
				System.out.println("update position");
				System.out.println("update training");
				System.out.println(divider);
			}
	
			else if(choose.equals("add employee")) {	
				emp.addEmployee();	
			}
			else if(choose.equals("add benefits")) {				
				ben.addBenefits();
			}
			else if(choose.equals("add pay")) {
				pay.setPayrate();				
			}
			else if(choose.equals("add position")) {
				fin.addPosition();;				
			}
			else if(choose.equals("add training")) {
				fin.addTraining();				
			}
			else if(choose.equals("add direct deposit")) {
				pay.deposit();				
			}
			else if(choose.equals("find employee")) {
				emp.findEmployee();	
			}
			else if(choose.equals("find pay info")) {
				pay.findPayAll();
			}
			else if(choose.equals("find pay")) {
				pay.findPayrate();				
			}
			else if(choose.equals("employees in training")) {
				fin.findEmpInTrain();
			}
			else if(choose.equals("find benefits")) {				
				ben.findBenefits();
			}
			else if(choose.equals("update employee")) {
				emp.updateEmployee();
			}
			else if(choose.equals("update Pay info")) {
				pay.updatePay();				
			}
			else if(choose.equals("update position")) {
				fin.updatePosition();				
			}
			else if(choose.equals("update training")) {
				fin.updateTraining();				
			}
			else if(choose.equals("update benefits")) {				
				ben.updateBenefits();
			}
			else if(choose.equals("delete pay")) {
				pay.deletePay();
			}
			else if(choose.equals("delete employee")) {	
				emp.deleteEmployee();
			}
			else if(choose.equals("delete benefits")) {				
				ben.deleteBenefits();
			}
			else if(choose.equals("clock out")) {
				emp.employeeOut();
				System.out.println("Youre out");
				quit = true;
			}
			else if (choose.equals("quit")) {
				quit = true;
			}
		}
	}
	public void PromptA() {
		//after differentiating between access of employees vs administrators, Primary Key will get employee clock in
		//in employee prompt, able to view all employee information for every aspect able to be viewed by administrator and employee
	}
}
