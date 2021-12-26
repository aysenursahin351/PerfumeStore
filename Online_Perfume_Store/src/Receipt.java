
//The object that holds data about an order
public class Receipt
{
    private int orderId;
    private float totalPrice;
    private String purchasedTime;
    private Customer customer;
    private Basket items;
    private String situation;
    
    
    public Receipt(int orderId, Customer customer, Basket items, float totalPrice,
               String purchasedTime)
    {
        // initialise instance variables
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.purchasedTime = purchasedTime;
        this.totalPrice = totalPrice;
    }

    
    public String getPurchasedDate()
    {
        return purchasedTime;
    }
   
    
    public String getSituation() {
		return situation;
	}
    
    //holds the informaiton if customer requested for repayment
	public void setSituation(String situation) {
		this.situation = situation;
	}
    
    public float getTotalPrice()
    {
        return totalPrice;
    }
    
    public int getOrderId()
    {
        return orderId;
    }
    
   
    public Basket getItems()
    {
        return items;
    }
}
