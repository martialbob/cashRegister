package r;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;



public class cashRegister 
{
	
	private double profitMargin = .3;
	private double salesTax = .06;
	private boolean registerOn = false;
	private String dataFile = "database.txt"; 
	private Vector<Inventory> database;
	private Scanner cin = new Scanner(System.in);
	private double calculatedPrice = 0;
	private Scanner inputStream = null;

	
	
	
	public cashRegister()
	{
		//finding and opening data file
		try {
			inputStream = new Scanner(
			        new BufferedReader(
			          new FileReader(dataFile)));
		} 
		catch (FileNotFoundException e1) 
		{
			System.out.println("File not found.\nExiting program");
			System.exit(001);
		}
		
		//setting the cash register power to on
		registerOn = true;		

		//if file found create a database from database.txt file
		try {
			createDatabase();
		} catch (IOException e) {
			System.out.println("Error creating database.\nEnding Program.");
			System.exit(007);
		}
		//we have a database(based on the vector class) to work with... now lets do some work!
		while(registerOn)
		{
			//lets print a menu and see what the user wants to do!
			int decision = printMenu();
			//lets take what the user wanted to do and execute it!
			commands(decision);
			
		}
	}
	private void createDatabase() throws IOException 
	{
		//creating a vector
		database = new Vector<Inventory>();
		while(inputStream.hasNext())
		{
			//building inventory items... putting data into them, and adding them to our database vector
			Inventory temp = new Inventory();
			temp.setItemNumber(inputStream.nextInt());
			temp.setCost(inputStream.nextDouble());
			temp.setQuantity(inputStream.nextInt());
			database.add(temp);
		}
	}
	private int printMenu()
	{
		boolean validInput = false;
		int decision = 0;
		while(!validInput)
		{
			validInput = true;
			//printing menu
			System.out.println("What would you like to do?");
			System.out.println("1. Ring up an item.");
			System.out.println("2. Display all item numbers and associated cost");
			System.out.println("3. Shut down the cash register");
			System.out.println("Please enter an option");
			//getting decision
			cin = new Scanner(System.in);
			String input = cin.nextLine();
			//lets see if its a number
			try
			{
				decision = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Your input was not a number, please try again");
				validInput = false;
			}
			
		}
		//it was a number!! lets return it!
		return decision;
	}
	private void commands(int decision)
	{
		int temp = 0;
		Inventory foundObj = null;
		if (decision == 1)
		{
			boolean validInput = false;
			while(!validInput)
			{
				validInput = true;
				//Ask the user for an item number and get the item number
				System.out.println("Enter an item number");
				String input = cin.next();
				try
				{
					temp = Integer.parseInt(input);
				}
				catch(NumberFormatException e)
				{
					System.out.println("Your input was not a number, please try again");
					validInput = false;
				}
				//we now have a valid number for itemNumber... lets see if we have it
				if(validInput)
				{
					if(findItemNumber(temp))
						foundObj = getItemIndex(temp);
					else
					{
						System.out.println("Item number not found");
						validInput = false;
					}
				}
			}
			//we now have the item we were looking for!!! lets tell the user we found it
			System.out.println("Item found");
			//get from user how many of item they want to purchase
			validInput = false;
			int quantityToPurchase = 0;
			while(!validInput)
			{
				System.out.println("How many items of " + foundObj.getItemNumber() + 
						" would you like to purchase?");
				String input = cin.next();
				validInput =  true;
				try
				{
					quantityToPurchase = Integer.parseInt(input);
				}
				catch (NumberFormatException e)
				{
					System.out.println("Not an integer.\nPlease input an integer value");
				}
			}
			//we now have how many of the item the user wanted to purchase..
			//now lets do some calculations!
			//calculating profit margin
			calculateProfit(foundObj, quantityToPurchase);
			//calculate sales tax
			calculateTax();
			System.out.println("Total with sales tax is " + calculatedPrice + ".");
			
		}
		else if (decision == 2)
		{
			printDatabase();
		}
		else if (decision == 3) // the user wants to turn the cashRegister off... lets do that!
		{
			shutDown();
		}
	}
	private void shutDown()
	{
		registerOn = false;
		System.out.println("Shutting the Cash Register Down.\n Have a great day!");
		System.exit(0);
	}
	private boolean findItemNumber(int itemNumber)
	{
		
		Inventory temp = null;
		for(int i = 0; i < database.size(); i++)
		{
			temp = database.get(i);
			if(itemNumber == temp.getItemNumber())
				return true;
		}		
		return false;
	}
	private Inventory getItemIndex(int itemNumber)
	{
		Inventory temp = null;
		for(int i = 0; i < database.size(); i++)
		{
			temp = database.get(i);
			if(itemNumber == temp.getItemNumber())
				return temp;
		}
		System.out.println("The item you said existed doesn't exist anymore.\nThis should never happen.\nExiting program with error 003");
		System.exit(003);
		return null;
	}
	private void calculateProfit(Inventory object, int quantity)
	{
		calculatedPrice = (object.getCost() * quantity) * (1 - profitMargin);
	}
	private void calculateTax()
	{
		calculatedPrice = calculatedPrice * (1 - salesTax);
	}
	private void printDatabase()
	{
		Inventory temp = null;
		for(int i = 0; i < database.size(); i++)
		{
			temp = database.get(i);
			System.out.println("Item Number: " + temp.getItemNumber() + " Item Cost: " + temp.getCost());
		}
	}
} 