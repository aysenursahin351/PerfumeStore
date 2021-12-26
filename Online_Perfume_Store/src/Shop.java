import java.util.List;
import java.util.ArrayList;

//This class must be implemented
public interface Shop {
	
	//Returns all perfumes
	public List<Perfume> getPerfumes();
	
	//Checks if login was succesful
	public boolean login(String username, String password);
	
	//Method for new customers to create a new account
	public boolean signup(String username, String password);
	
	//Total price of the basket
	
	public float getPrice(Basket basket);
	
	
	public Customer getUserInfo(String username);
	
	//Changes customer or admin info
	public boolean setUserInfo(String username, Customer info);
	
	
	//Caller that gives order to run transactions after confirmed checkout
	
	public boolean processOrder(Customer customer, Basket basket, float total_price);
	
	
	public boolean addPerfume(Perfume p);
	
	//For updating quantity
	public boolean addToPerfumes(Perfume p, int quantity);
	
	//Read tester text file
	public void ReadTester();
	
	public ArrayList<Receipt> getReceiptList();

	//Look for orders between specified times
	public boolean searchPurchasedItems(int startDate, int endDate, String currentUserID);

	//Use this method to add comments to comment queue when you looking at purchased items. 
	public void addComplains(Complain co);

	public Queue getComments();

	
}
