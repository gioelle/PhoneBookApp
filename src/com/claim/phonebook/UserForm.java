package com.claim.phonebook;

import java.util.ArrayList;
import java.util.Scanner;

public class UserForm {
	private int selection = 0;
	private String menuLang = "Please select from the following:\n";
	private String record = "1 - Add New Record\n";
	private String search = "2 - Search for a Record\n";
	private String printAll = "3 - Display Phone Book\n";
	private String exit = "0 - Exit Phonebook Application\n";
	private String menu = menuLang + record + search + printAll + exit;
	private Person searchResult;
	private boolean flag;
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
			getRecord();
			userForm();
			break; 

		case 2: //Search for a record
			defineSearchMethod();
			break;
		case 3: //display whole phonebook
			printDatabase();
			break;
		case 0:   //end the program
			System.out.println("Thanks for using the WhitePages");
			System.exit(0);
			break;
		default:  
			//handle incorrect selection entered, prompt user form
			System.out.print("Please enter a valid selection (0-8)");
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
		if (record.length < 6){
			System.out.println("Incorrect entry, please try again.");
			menuActions(1);
		}

		int arrayLength = record.length-1;
		if(flag == false){
			Person person = new Person(record, fullNameSplit(record));
			phoneBook.add(person);
			System.out.println("Record Added.");
			return null;
		} else System.out.println("Record already exists.");
		flag = false;
		return record[arrayLength];
	}

	public String[] fullNameSplit (String[] record){
		String fullNameHolder = record[0];
		String [] fullName = fullNameHolder.split(" ");
		return fullName;
	}

	public void defineSearchMethod(){
		int searchMethod;
		System.out.println("How would you like to search for a record? 1: First Name 2: Last Name 3: Telephone 4: City 5: State");
		searchMethod = input.nextInt();

		switch (searchMethod){
		case 1: 
			nextOption(searchFirstName());
			break;
		case 2: 
			nextOption(searchLastName());
			break;
		case 3: 
			nextOption(searchTelephone(getTelephone()));
			break;
		case 4: 
			nextOption(searchCity());
			break;
		case 5: 
			nextOption(searchState());
			break;
		default: System.out.println("Please enter a valid search method: 1, 2, 3, 4, or 5");		
		break;
		}
	}

	//	//Overloaded method takes menuAction specifically to handle search by telephone case, which may be unnecessary.
	//	public void defineSearchMethod(int menuAction){
	//		int searchMethod = menuAction;
	//
	//		if(searchMethod == 0){
	//			System.out.println("How would you like to search for a record? 1: First Name 2: Last Name 3: Telephone 4: City 5: State");
	//			searchMethod = input.nextInt();
	//		} 
	//
	//		switch (searchMethod){
	//		case 1: 
	//			searchFirstName();
	//			break;
	//		case 2: 
	//			searchLastName();
	//			break;
	//		case 3: 
	//			searchTelephone(getTelephone());
	//			break;
	//		case 4: 
	//			searchCity();
	//			break;
	//		case 5: 
	//			searchState();
	//			break;
	//		default: System.out.println("Please enter a valid search method: 1, 2, 3, 4, or 5");		
	//		break;
	//		}
	//	}

	public void nextOption(ArrayList<Integer> indexPositions){
		System.out.println("Please select: 1 - Update, 2 - Delete, 3 - Main Menu");
		Scanner read = new Scanner(System.in);
		int option = read.nextInt();
		int selection;
		int i = -1;
		read.nextLine();
		if(option != 3){
			System.out.println("Which number record would you like to update?");
			selection = read.nextInt();
			read.nextLine();
			i = indexPositions.get(selection-1);
		}
		switch (option){
		case 1: //Update Record
			updateRecord(i);
			System.out.println(phoneBook.get(i).toString() + " record updated.");
			userForm();
			break;
		case 2: //Delete Record
			phoneBook.remove(i);
			System.out.println(phoneBook.get(i).toString() + " record removed.");
			userForm();
			break;
		case 3: //Return to Menu
			userForm();
			break;
		default: 
			System.out.println("Invalid selection.");
			nextOption(indexPositions);
			break;
		}
	}



	public ArrayList<Integer> searchFirstName(){
		ArrayList<Integer> resultsArray = new ArrayList<Integer>();
		System.out.println("Please enter a first name to search.");
		String firstName = input.next();
		int counter = 0;
		for (int i =0; i< phoneBook.size(); i++){
			if (phoneBook.get(i).getFirstName().equals(firstName)){
				++counter;
				System.out.println("Record: " + counter + " " +  phoneBook.get(i).getFirstName() + ' ' + phoneBook.get(i).getLastName() + ' ' + formatPhone(phoneBook.get(i).getTelephone())+ ' ' + phoneBook.get(i).getAddress());
				resultsArray.add(i);
				break;
			}
		}
		return resultsArray;
	}


	//	public void nextOption(boolean flag){
	//		System.out.println("Please select: 1 - Update, 2 - Delete, 3 - Main Menu");
	//		Scanner read = new Scanner(System.in);
	//		int option = read.nextInt();
	//		switch (option){
	//		case 1: //Update Record
	//			updateRecord();
	//			break;
	//		case 2: //Delete Record
	//			deleteRecord(getTelephone());
	//			break;
	//		case 3: //Return to Menu
	//			userForm();
	//			break;
	//		default: 
	//			System.out.println("Invalid selection.");
	//			nextOption(1);
	//			break;
	//		}
	//	}

	public ArrayList<Integer> searchLastName(){
		ArrayList<Integer> resultsArray = new ArrayList<Integer>();
		System.out.println("Please enter a last name to search.");
		String lastName = input.next();
		int counter = 0;
		for (int i =0; i< phoneBook.size(); i++){
			if (phoneBook.get(i).getLastName().equals(lastName)){
				++counter;
				System.out.println("Record: " + counter + " " + phoneBook.get(i).getFirstName() + ' ' + phoneBook.get(i).getLastName() + ' ' + formatPhone(phoneBook.get(i).getTelephone())+ ' ' + phoneBook.get(i).getAddress());
				resultsArray.add(i);
				break;
			}
			searchResult = phoneBook.get(i);
		}
		System.out.println(searchResult);
		return resultsArray;
	}

	public ArrayList<Integer> searchCity(){
		ArrayList<Integer> resultsArray = new ArrayList<Integer>();
		System.out.println("Please enter a city to search by. Example: Columbia");
		String city = input.next();
		int counter = 0;
		for (int i =0; i< phoneBook.size(); i++){
			if (phoneBook.get(i).getAddress().getCity().equals(city)){
				++counter;
				System.out.println("Record: " + counter + " " + phoneBook.get(i).getFirstName() + ' ' + phoneBook.get(i).getLastName() + ' ' + formatPhone(phoneBook.get(i).getTelephone())+ ' ' + phoneBook.get(i).getAddress());
				resultsArray.add(i);
			}
		}
		return resultsArray;
	}

	public ArrayList<Integer> searchState(){
		int counter = 0;
		ArrayList<Integer> resultsArray = new ArrayList<Integer>();
		System.out.println("Please enter a State to search by. Example: Missouri");
		String state = input.next();
		for (int i =0; i< phoneBook.size(); i++){
			if (phoneBook.get(i).getAddress().getState().equals(state)){
				++counter;
				System.out.println("Record: " + counter + " " + phoneBook.get(i).getFirstName() + ' ' + phoneBook.get(i).getLastName() + ' ' + formatPhone(phoneBook.get(i).getTelephone())+ ' ' + phoneBook.get(i).getAddress());
				resultsArray.add(i);
				break;
			}
			searchResult = phoneBook.get(i);
		}
		System.out.println(searchResult);
		return resultsArray;
	}

	//gets telephone number from user
	public String getTelephone(){
		System.out.println("Please enter 10 digit telephone number without spaces.");
		Scanner telephoneInput = new Scanner(System.in);
		String telephone = telephoneInput.nextLine();
		//test telephone
		System.out.println(telephone);
		return telephone.trim();
	}

	public ArrayList<Integer> searchTelephone(String telephone){
		ArrayList<Integer> resultsArray = new ArrayList<Integer>();
		int cont;
		for (Person p : phoneBook) {
			if (p.getTelephone().trim().equals(telephone.trim())){
				System.out.println("Record: 1 " + p.getFirstName() + ' ' + p.getLastName() + ' ' + formatPhone(p.getTelephone())+ ' ' + p.getAddress());
				resultsArray.add(phoneBook.indexOf(p));
				flag = true; 
				break;
			}
		}
		if(!flag){ 
			System.out.println("No record exists. Enter 1 to return to Menu or 7 to exit.");
			cont = input.nextInt();
			if(cont == 1){
				userForm();
			} else {
				menuActions(7);
			}
		}
		return resultsArray;
	}	

	public void updateRecord(int i){
		System.out.println("What would you like to update? 1 - First Name 2 - Middle Name 3 - Last Name 4 - Telephone 5 - Street Address 6 - City 7 - State 8 - ZipCode"); 
		Scanner sc1 = new Scanner (System.in);
		int updateCommand = sc1.nextInt();
		sc1.nextLine();
		switch (updateCommand){
		case 1:
			System.out.println("Enter the updated first name.");
			String updatedFirst = sc1.nextLine();
			phoneBook.get(i).setFirstName(updatedFirst);
			break;
		case 2: 
			System.out.println("Enter the updated middle name(s).");
			String updatedMiddle = sc1.nextLine();
			phoneBook.get(i).setMiddleName(updatedMiddle);
			break;
		case 3: 
			System.out.println("Enter the updated last name.");
			String updatedLast = sc1.nextLine();
			phoneBook.get(i).setLastName(updatedLast);
			break;
		case 4: 
			System.out.println("Enter the updated telephone.");
			String updatedTelephone = sc1.nextLine();
			phoneBook.get(i).setTelephone(updatedTelephone);
			break;
		case 5:
			System.out.println("Enter the updated street address.");
			String updatedStreet = sc1.nextLine();
			phoneBook.get(i).getAddress().setStreetAddress(updatedStreet);
			break;
		case 6:
			System.out.println("Enter the updated city.");
			String updatedCity = sc1.nextLine();
			phoneBook.get(i).getAddress().setCity(updatedCity);
			break;
		case 7: 
			System.out.println("Enter the updated State.");
			String updatedState = sc1.nextLine();
			phoneBook.get(i).getAddress().setState(updatedState);
			break;
		case 8: 
			System.out.println("Enter the updated Zipcode.");
			String updatedZip = sc1.nextLine();
			phoneBook.get(i).getAddress().setZipcode(updatedZip);
			break;
		default:
			System.out.println("Enter a valid selection.");
			updateRecord(i);
			break;

		}
	}

	public void deleteRecord(int positionToDelete){
		phoneBook.remove(positionToDelete);
		System.out.println("Record removed.");
	}


	public void deleteRecord(String telephone){
		Person toBeRemoved = null;
		for(int i=0; i<phoneBook.size(); i++){
			//System.out.println("Phone records : "+phoneBook.get(i).getTelephone().trim()+" "+telephone+phoneBook.get(i).getTelephone().trim().equals(telephone.trim()));
			if(phoneBook.get(i).getTelephone().trim().equals(telephone.trim())){

				toBeRemoved = phoneBook.remove(i);				
				break;
			}
		}
		if(toBeRemoved == null){
			System.out.println("Telephone record: " + telephone + " not found.");
		}
	}
	
	public void printDatabase(){
		if(phoneBook.size() > 0){
			for(int i = 0; i<phoneBook.size(); i++){
			System.out.println(phoneBook.get(i).toString() + "\n");
			} 
		}
		else {
			System.out.println("Please feed me records, I'm empty!");
		}
	}
	
	public static String formatPhone(String telephone){
		String formatted = "(   )   -    ;";
		formatted = "(" + telephone.charAt(0) + telephone.charAt(1) + telephone.charAt(2) + ")" + telephone.charAt(3) + telephone.charAt(4) + telephone.charAt(5) + "-" + telephone.charAt(6) + telephone.charAt(7) + telephone.charAt(8) + telephone.charAt(9); 
		return formatted;
	}
//	public void sortDatabase(){
//		phoneBook.sort(arg0)
//	}
//		
}

