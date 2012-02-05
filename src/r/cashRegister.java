package r;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;


public class cashRegister 
{
	
	private double profitMargin = .3;
	private double salesTax = .06;
	private boolean registerOn = false;
	private String dataFile = "database.txt"; 
	private Scanner inputStream = null;
	private Vector<Inventory> database;
	
	public cashRegister()
	{
		//setting the cash register power to on
		registerOn = true;
		
		//creating a input stream to get database from file
		try 
		{
			inputStream = new Scanner(new BufferedReader(new FileReader(dataFile)));
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Database file " + dataFile + " not found.  Ending program.\nError 001, see Readme for Solution.");
			System.exit(0);
		}
		createDatabase();
		while(registerOn)
		{
			
		}
	}
	private void createDatabase() 
	{
		database = new Vector<Inventory>();
		while(inputStream.hasNext())
		{
			Inventory temp = new Inventory();			
			String input = inputStream.next();
			temp.setItemNumber(Integer.parseInt(input));
			input = inputStream.next();
			temp.setCost(Double.parseDouble(input));
			input = inputStream.next();
			temp.setQuantity(Integer.parseInt(input));
			temp.getTotalCost();
			database.add(temp);
		}
	}
}
