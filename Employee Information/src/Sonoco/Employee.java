package Sonoco;

public class Employee {

	private String EmployeeFirstName;
	private String EmployeeLastName;
	private String SerialNumbers;
	private String Phone;
	private String OldPCName;
	private String NewPCName;
	private String Notes;
	private String Date;
	
	
	Employee(){
		this.EmployeeFirstName="";
		this.EmployeeLastName="";
		this.SerialNumbers="";
		this.Phone="";
		this.OldPCName="";
		this.NewPCName="";
		this.Notes="";
		this.Date="";
		
	}
	
	
	Employee(String first,String last,String serials,String phone,String oldPc,String newPc,String notes,String date){
		this.EmployeeFirstName=first;
		this.EmployeeLastName=last;
		this.SerialNumbers=serials;
		this.Phone=phone;
		this.OldPCName=oldPc;
		this.NewPCName=newPc;
		this.Notes=notes;
		this.Date=date;
	}
	public String getEmployeeFirstName() {
		return EmployeeFirstName;
	}
	public String getEmployeeLastName() {
		return EmployeeLastName;
	}
	public String getSerialNumbers() {
		return SerialNumbers;
	}
	public String getPhone() {
		return Phone;
	}
	public String getOldPCName() {
		return OldPCName;
	}
	public String getNewPCName() {
		return NewPCName;
	}
	public String getNotes() {
		return Notes;
	}
	public String getDate() {
		return Date;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		EmployeeFirstName = employeeFirstName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		EmployeeLastName = employeeLastName;
	}
	public void setSerialNumbers(String serialNumbers) {
		SerialNumbers = serialNumbers;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public void setOldPCName(String oldPCName) {
		OldPCName = oldPCName;
	}
	public void setNewPCName(String newPCName) {
		this.NewPCName = newPCName;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public void setDate(String date) {
		Date = date;
	}
}
