package r;

public class Inventory 
{
	//Private Variables
	private int itemNumber;
	private int quantity;
	private double cost;
	private double totalCost;
	
	//Constructors
	//Default
	public Inventory()
	{
		this.itemNumber = 0;
		this.quantity = 0;
		this.cost = 0;
		this.totalCost = 0;
	}
	//Overloaded
	public Inventory(int itemNumber, int quantity, double cost)
	{
		this.itemNumber = checkPositive(itemNumber);
		this.quantity = checkPositive(quantity);
		this.cost = checkPositive(cost);
	}
	//Set functions
	public void setItemNumber(int itemNumber)
	{
		this.itemNumber = checkPositive(itemNumber);
	}
	public void setQuantity(int quantity)
	{
		this.quantity = checkPositive(quantity);
	}
	public void setCost(double cost)
	{
		this.cost = checkPositive(cost);
	}
	private void setTotalCost()
	{
		this.totalCost = this.quantity*this.cost;
	}
	//Get functions
	public int getItemNumber()
	{
		return this.itemNumber;
	}
	public int getQuantity()
	{
		return this.quantity;
	}
	public double getCost()
	{
		return this.cost;
	}
	public double getTotalCost()
	{
		setTotalCost();
		return this.totalCost;
	}
	//Negative checker functions
	private int checkPositive(int value)
	{
		if(value < 0)
			return value*-1;
		else
			return value;
	}
	private double checkPositive(double value)
	{
		if(value < 0)
			return value*-1;
		else
			return value;
	}
}
