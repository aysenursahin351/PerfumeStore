
//This is the class holds data about user except passwords because they are stored in a hash in perfumeShop class
public class Customer {

	//name
	public String name;
	
	//address
	public String address;
	
	
	public String cardNumber;
	
	
	public String phoneNumber;
	
	public String type;
	
	//constructor
	public Customer(String name, String address, String cardNumber, String phoneNumber, String type) {
		this.name = name;
		this.address = address;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
	
	// For cheching if user is admin or not
	public String getType()
	{
	    return type;
	}
}
