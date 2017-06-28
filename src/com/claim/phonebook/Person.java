package com.claim.phonebook;


public class Person {
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String telephone;
	private Address address;
	private String[] fullName;
	                          

	//Default Constructor
	public Person(){
		
	}
	
	//Parameterized Constructor 
	public Person(String[] record, String[] fullName){
		this.firstName = fullName[0];
		for(int i = 1; i<fullName.length -1; i++){
			middleName += fullName[i];
			}
		this.lastName = fullName[fullName.length-1];
		address = new Address();
		this.address.setStreetAddress(record[1]);
		this.address.setCity(record[2]);
		this.address.setState(record[3]);
		this.address.setZipcode(record[4]);	
		this.telephone = record[5];
	}
	
	
	public String toString(){
		return "First Name is: " + this.firstName + "\nLast Name is: " + this.lastName + "\nTelephone Number is: " + UserForm.formatPhone(this.telephone);
	}
	//getters & setters
	public String[] getFullName(){
		return this.fullName;
	}
	public void setFirstName(String[] fullName){
		this.fullName = fullName;
	}
		
	public String getFirstName(){
		return this.firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getMiddleName(){
		return this.middleName;
	}
	public void setMiddleName(String middleName){
		this.middleName = middleName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getTelephone(){
		return this.telephone;
	}
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	
	//this instance of getters & setters speaks between classes: Student & Address
	public Address getAddress(){
		return this.address;
	}
	public void setAddress(Address address){
		this.address = address;
	}
}