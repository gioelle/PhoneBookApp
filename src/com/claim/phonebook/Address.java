package com.claim.phonebook;

public class Address {
	
	private String streetAddress;
	private String city;
	private String state;
	private String zipcode;
	
	//Default Constructor
	public Address (){
	}
	//Parameterized Constructor
	public Address (String streetAddress, String city, String state, String zipcode){
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}
	
	//this is fucking awesome
	public String toString(){
		return this.streetAddress + "\n" + this.city + "\n" + this.state + "\n" + this.zipcode;
	}
	
	
	//Getters & setters
	public String getStreetAddress(){
		return this.streetAddress;
	}
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	public String getCity(){
		return this.city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getZipcode(){
		return this.zipcode;
	}
	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}
}
