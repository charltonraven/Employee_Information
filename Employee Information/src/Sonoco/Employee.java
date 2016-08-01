package Sonoco;

import javafx.beans.property.SimpleStringProperty;

public class Employee {

	private SimpleStringProperty EmployeeFirstName;
	private SimpleStringProperty EmployeeLastName;
	private SimpleStringProperty SerialNumbers;
	private SimpleStringProperty Phone;
	private SimpleStringProperty OldPCName;
	private SimpleStringProperty NewPCName;
	private SimpleStringProperty Notes;
	private SimpleStringProperty Date;
	
	
	Employee(){
		this.EmployeeFirstName=new SimpleStringProperty();
		this.EmployeeLastName=new SimpleStringProperty();
		this.SerialNumbers=new SimpleStringProperty();
		this.Phone=new SimpleStringProperty();
		this.OldPCName=new SimpleStringProperty();
		this.NewPCName=new SimpleStringProperty();
		this.Notes=new SimpleStringProperty();
		this.Date=new SimpleStringProperty();
		
	}
	
	
	Employee(String first,String last,String phone,String serials,String oldPc,String newPc,String notes,String date){
		this.EmployeeFirstName=new SimpleStringProperty(first);
		this.EmployeeLastName=new SimpleStringProperty(last);
		this.SerialNumbers=new SimpleStringProperty(serials);
		this.Phone=new SimpleStringProperty(phone);
		this.OldPCName=new SimpleStringProperty(oldPc);
		this.NewPCName=new SimpleStringProperty(newPc);
		this.Notes=new SimpleStringProperty(notes);
		this.Date=new SimpleStringProperty(date);
	}
	Employee(String first,String last,String phone,String serials,String oldPc,String newPc,String notes){
		this.EmployeeFirstName=new SimpleStringProperty(first);
		this.EmployeeLastName=new SimpleStringProperty(last);
		this.SerialNumbers=new SimpleStringProperty(serials);
		this.Phone=new SimpleStringProperty(phone);
		this.OldPCName=new SimpleStringProperty(oldPc);
		this.NewPCName=new SimpleStringProperty(newPc);
		this.Notes=new SimpleStringProperty(notes);

	}



	public String getEmployeeFirstName() {
		return EmployeeFirstName.get();
	}


	public String getEmployeeLastName() {
		return EmployeeLastName.get();
	}


	public String getSerialNumbers() {
		return SerialNumbers.get();
	}


	public String getPhone() {
		return Phone.get();
	}


	public String getOldPCName() {
		return OldPCName.get();
	}


	public String getNewPCName() {
		return NewPCName.get();
	}


	public String getNotes() {
		return Notes.get();
	}


	public String getDate() {
		return Date.get();
	}


	public void setEmployeeFirstName(String employeeFirstName) {
		EmployeeFirstName.set(employeeFirstName);
	}


	public void setEmployeeLastName(String employeeLastName) {
		EmployeeLastName.set(employeeLastName);
	}


	public void setSerialNumbers(String serialNumbers) {
		SerialNumbers.set(serialNumbers);
	}


	public void setPhone(String phone) {
		Phone.set(phone);
	}


	public void setOldPCName(String oldPCName) {
		OldPCName.set(oldPCName);;
	}


	public void setNewPCName(String newPCName) {
		NewPCName.set(newPCName);;
	}


	public void setNotes(String notes) {
		Notes.set(notes);;
	}


	public void setDate(String date) {
		Date.set(date);;
	}
	
}
