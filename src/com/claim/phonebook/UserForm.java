package com.claim.phonebook;

import java.util.ArrayList;
import java.util.Scanner;

public class UserForm {
	private int selection = 0;
	private String menuLang = "Please select from the following:\n";
	private String record = "1 - Add New Record\n";
	private String delete = "2 - Delete a Record\n";
	private String searchTele = "3 - Locate Record by Telephone Number\n";
	private String searchFirstName = "4 - Locate Record by First Name\n";
	private String searchLastName = "5 - Locate Record by Last Name\n";
	private String update = "6 - Update a Record\n";
	private String exit = "7 - Exit Phonebook Application\n";
	private String menu = menuLang + record + delete + searchTele + searchFirstName + searchLastName + update + exit;
	ArrayList<Person> phoneBook = new ArrayList<Person>();
	Scanner input = new Scanner(System.in);

	//menu to display
	public void userForm(){
		System.out.print(menu);
		selection = input.nextInt();
		menuActions(selection);
	}

	//use this switch case to handle the user input, call a method
	public void menuActions(int selection){
		switch (selection){
		case 1:  //Add a new Record
			searchTelephone(1, getRecord());

			userForm();
			break; 
		case 2: //Delete a record
			deleteRecord(getTelephone());

			userForm();
			break;
		case 3:  //Search for a record by telephone
			defineSearchMethod(3);
			userForm();
			break;
		case 4:  //Search by First Name
			defineSearchMethod(1);

			userForm();
			break;
		case 5:  //Search by Last Name
			defineSearchMethod(2);

			userForm();
			break;
		case 6:  //discover preferred search method & call it to search for record & display results
			defineSearchMethod(0);

			userForm();
			break;
		case 7:   
			System.exit(0);
			break;
		default:  
			//incorrect selection entered, prompt user form
			System.out.print("Please enter a valid selection (1-7)");
			userForm();
			break;
		}
	}

	//get user record input
	public String getRecord(){
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter record data: firstName middleName lastName, streetAddr, city, state, zip, telephone");
		String rawInput = sc1.nextLine();
		String[] record = rawInput.split(",");
		int arrayLength = record.length-1;
		if(!searchTelephone(1, record[arrayLength])){
			Person person = new Person(record, fullNameSplit(record));
			phoneBook.add(person);
			System.out.println("Record Added.");
		} else System.out.println("Record already exists.");
		return record[arrayLength];
	}
	
	public String[] fullNameSplit (String[] record){
		String fullNameHolder = record[0];
		String [] fullName = fullNameHolder.split(" ");
		return fullName;
		}


	public void defineSearchMethod(int menuAction){
		int searchMethod = menuAction;
		if(searchMethod == 0){
			System.out.println("How would you like to search for a record? 1: First Name 2: Last Name 3: Telephone");
			searchMethod = input.nextInt();
		} 

		switch (searchMethod){
		case 1: 
			searchFirstName();
			break;
		case 2: 
			searchLastName();
			break;
		case 3: 
			searchTelephone(3, getTelephone());
			break;
		default: System.out.println("Please enter a valid search method: 1, 2, or 3");		
		break;
		}
	}



	public String getTelephone(){
		System.out.println("Please enter 10 digit telephone number without spaces.");
		String telephone = input.next();
		String.out.println(telephone);
		return telephone;
	}

	public void searchFirstName(){
		System.out.println("Please enter a first name to search.");
		String firstName = input.next();
		for (Person p : phoneBook) {
			if (p.getFirstName().equals(firstName)){
				System.out.println(p.getFirstName() + ' ' + p.getLastName() + ' ' + p.getTelephone()+ ' ' + p.getAddress());
				break;
			}
		}
	}

	public void searchLastName(){
		System.out.println("Please enter a last name to search.");
		String lastName = input.next();
		for (Person p : phoneBook) {
			if (p.getLastName().equals(lastName)){
				System.out.println(p.getFirstName() + ' ' + p.getLastName() + ' ' + p.getTelephone()+ ' ' + p.getAddress());
				break;
			}
		}
	}


	public void deleteRecord(String telephone){
		for(int i=0; i<phoneBook.size(); i++){
		
			if(phoneBook.get(i).getTelephone().equals(telephone)){
				
				phoneBook.remove(i);
				System.out.println("Record removed.");
				break;
			}
			//else System.out.println("Record not found.");
		}
	}

	public boolean searchTelephone(int menuAction, String telephone){
		System.out.println("We begin the telephone search");
		boolean flag = false;
		int cont;
		for (Person p : phoneBook) {
			System.out.println("Entering the for loop of telephone search");
			if (p.getTelephone().equals(telephone)){
				if(menuAction == 3) {System.out.println(p.getFirstName() + ' ' + p.getLastName() + ' ' + p.getAddress() + ' ' + p.getTelephone());}
				flag = true; 
				break;
			}
		}
		if(!flag){ 
			System.out.println("flag = false");
			if(menuAction == 3) {
				System.out.println("No record exists. Enter 1 to return to Menu or 7 to exit.");
				cont = input.nextInt();
				if(cont == 1){
					userForm();
				} else {
					menuActions(7);
				}
			}
			flag = false;
		}
		return flag;
	}	

}
