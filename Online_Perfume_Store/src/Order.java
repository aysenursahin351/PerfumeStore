import java.util.Date;
import java.text.SimpleDateFormat;

// This class creates an object that holds data bout user and order for example: date total price 
public class Order
{
    private int orderId;
    private Basket basket;
    private float total_price;
    private Customer customer;
    private String purchasedTime;
    
    //Constructor
    public Order(int currentMaxId, Customer currentCustomer, Basket currentBasket, float total_price)
    {
        // initialise instance variables
        orderId = ++currentMaxId;
        customer = currentCustomer;
        basket = currentBasket;
        this.total_price = total_price;
        purchasedTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }

    
    public int getOrderId()
    {
        return orderId;
    }
    
    
    public Basket getBasket()
    {
        return basket;
    }
    
    
    public float getTotalPrice()
    {
        return total_price;
    }
    
    
    public Customer getCustomer()
    {
        return customer;
    }
    
    
    public String getPurchasedTime()
    {
        return purchasedTime;
    }
}
