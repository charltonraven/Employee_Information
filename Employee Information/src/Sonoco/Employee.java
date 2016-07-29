package Sonoco;

public class Employee {

	public String EmployeeFirstName;
	public String EmployeeLastName;
	public String SerialNumbers;
	public String Phone;
	public String OldPCName;
	public String newPCName;
	public String Notes;
	public String Date;
	
	
	Employee(){
		
	}
	Employee(String first,String last,String serials,String phone,String oldPc,String newPc,String notes,String date){
		this.EmployeeFirstName=first;
		this.EmployeeLastName=last;
		this.SerialNumbers=serials;
		this.Phone=phone;
		this.OldPCName=oldPc;
		this.newPCName=newPc;
		this.Notes=notes;
		this.Date=date;
	}
}
