package r;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class cashRegister 
{
	
	private double profitMargin = .3;
	private double salesTax = .06;
	private boolean registerOn = false;
	private String dataFile = "database.txt"; //TODO CREATE TEXT FILE AND FILL WITH AN INVENTORY DATABASE
	@SuppressWarnings("unused")
	private Scanner inputStream = null;
	
	public cashRegister()
	{
		//setting the cash register   mpower to on
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
		while(inputStream.hasNext())
		{
			System.out.println(inputStream.next());
		}
	}
}
