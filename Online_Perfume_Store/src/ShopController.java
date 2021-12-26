import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.List;

//Controller class that most of the classes must call for reaching PerfumeShop

public class ShopController {

   
    //default icon
    public static ImageIcon NO_IMAGE_ICON = generateIcon("https://www.google.com/search?q=perfume&rlz=1C1NDCM_trTR935TR935&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjqqJSzj5_xAhWdhf0HHff8DlQQ_AUoAXoECAIQAw&biw=1536&bih=696", 150, 150);
    
    // Logo for main screen
    public static ImageIcon LOGO_ICON = new ImageIcon(ShopController.class.getResource("logo.png"));
   
    
    //image cache
    public static HashMap<String, ImageIcon> IMAGE_CACHE;
    
    //Generates an icon to use around program
    public static ImageIcon generateIcon(String imgLoc, int width, int height){
        if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
        if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
        try {
            URL url = new URL(imgLoc);
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            IMAGE_CACHE.put(imgLoc, icon);
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    private JFrame window = new JFrame();
    private Shop backend;
    
    private Basket basket = new Basket();
    private String currentUserID;
    
    //Constructor
    public ShopController(Shop b){
        this.backend = b;
    }
    
   //Select a screen to show
    public void setScreen(Screen screen){
        screen.setController(this);
        screen.initialize();
        screen.setBackground(Color.pink);
        window.setContentPane(screen);
        screen.setBackground(Color.pink);

        window.revalidate();
    }
    
    //jFrame we use to show screens
    public JFrame getWindow(){
        return window;
    }
    
    // Let us to work on current shop 
    public Shop getBackend(){
        return this.backend;
    }
    
    
    //This is where user interface starts to work
    //It starts with LoginScreen
    public void init(){
        window.setResizable(false);
        window.setTitle("Perfume Store");
        window.setSize(1200, 900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setScreen(new LoginScreen());
    }
    
    //Method for popups
    public void showPopup(String message){
        JOptionPane.showMessageDialog(window, message);
    }
    
    
    /*
     * LOGIN AND USERS
     * ------------------------------------------------
     */

    //customer details
    public Customer getCurrentCustomerDetails(){
        return getBackend().getUserInfo(currentUserID);
    }
    
    // Checks signup and looks if
    // name longer than 2 characters
    // pass longer than 4 characters
    // two passwords are same
    public void signup(String username, String pass, String confPass){
        
        // Ensuring length
        if(username.length() < 3) {
            showPopup("Your user ID must be at least 3 chars long!");
            return;
        }
        else if(pass.length() < 5){
            showPopup("Your password must be at least 5 chars long!");
            return;
        }
        else if(!pass.equals(confPass)){
            showPopup("The passwords do not match");
            return;
        }
        
        boolean success = getBackend().signup(username, pass);
        
        if(!success){
            showPopup("Signup failed, that userID may already be in use!");
        } else {
            showPopup("Your account has been created, please edit your details by clicking 'My account' in the top right.");
            attemptLogin(username, pass);
        }
    }
    
    // check if given info are true and if you are admin or normal
    public void attemptLogin(String username, String password){
        if(backend.login(username, password)){
            currentUserID = username;
            if (getCurrentUserType().equals("normal"))
                showProductList();
            if (getCurrentUserType().equals("admin"))
                showAdminView();
        } else {
            showPopup("Login failed! Please ensure that your user ID and password are correct.");
        }
    }
    
    // for changing user details
    public void updateUserDetails(Customer c){
        if(this.currentUserID != null){
            boolean success = getBackend().setUserInfo(this.currentUserID, c);
            if(!success){
                showPopup("There was an error saving your information! Please try again later.");
            }
        } else {
            System.err.println("Can't update user info, no one is signed in!");
        }
    }

    
    /*
     * Part about perfumes
     * ------------------------------------------------
     */

    //Checkoutpopup
    public void showCheckout(){
        CheckoutPopup.display(this);
    }
    
    //returns basket
    public Basket getBasket(){
         return basket;
    }
    
    // change quantity of given perfume
    public boolean addToPerfumes(Perfume p, int quantity)
    {
        boolean flag = false;
        if(getBackend().addToPerfumes(p, quantity))
        {
            showPopup("Successful");
            flag = true;
        }
        return flag;
    }
    
    
     // Adds a perfume to the basket. 
     
     
    public boolean addToCart(Perfume p, float quantity)
    {
        boolean flag = true;
        if (p == null)
        {
            showPopup("No selected items");
            flag = false;
        }
        else
        {
            if (quantity <= 0 || (int)quantity > p.getAmount())
            {
                showPopup("The quantity you have entered is invalid!!!!!!!!!!!!!!!!!!!!");
                flag = false;
            }
            else
            {
                if ((int)(basket.getQuantity(p)) < p.getAmount())
                {
                    basket.add(p, quantity);
                    showPopup("Successful");
                }
                else
                {
                    showPopup("Sorry, you cannot add");
                    flag = false;
                }
            }
        }
        return flag;
    }

    //shows basket
    public void showCartView(){
        setScreen(new BasketScreen());
    }
    //shows complians
    public void showComments(){
        setScreen(new ComplainScreen());
    }
    
    //total price of basket
    public float getTotalCartPrice(){
        return getBackend().getPrice(basket);
    }
    
    // perfumes showcase screen
    public void showProductList() {
        setScreen(new PerfumeListScreen());
    }

    //take current user and basket then tries to transaction
    public boolean attemptTransaction() {
        Customer c = getBackend().getUserInfo(currentUserID);
        String prefix = "Order failed! ";
        if(c.name.trim().equals("")){
            showPopup(prefix + "You have not entered your full name!");
            return false;
        }
        else if(c.address.trim().equals("")){
            showPopup(prefix + "You have not entered your home address!");
            return false;
        }
        else if(c.phoneNumber.trim().equals("")){
            showPopup(prefix + "You have not entered your phone number!");
            return false;
        }
        else if(c.cardNumber.trim().equals("")){
            showPopup(prefix + "You have not entered your card number!");
            return false;
        }
        
        boolean success = getBackend().processOrder(c, basket, getTotalCartPrice());
        
        if(!success){
            showPopup("Sorry, your order could not be placed! Please ensure that all of your information is correct.");
        }
        else {
            showPopup("Your order has been placed successfully! Have a nice day!");
            this.basket.clear();
            this.showCartView();
        }
        return success;
    }
    
    // checks if there is enough perfumes in stock by comparing your basket and stock 
    public boolean checkWithAvaiable()
    {
       // Basket newCart = new Basket();
        boolean flag = true;
        if(getBasket().getList().size() > 0)
        {
            for (int i=0; i<getBasket().getList().size(); i++)
            {
                if(getBasket().getList().get(i).getQuantity() > 
                        getBasket().getList().get(i).getPerfume().getAmount())
                {
                    showPopup("Order failed! You have chose items more than the available");
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                showCheckout();
            }
        }
        else
        {
            showPopup("Sorry. No items in the basket!!!!!!!!");
            flag = false;
        }
        return flag;
    }
    
     // Main screen for admin
    public void showAdminView()
    {
        setScreen(new AdminScreen());
    }
    
     // New item creation screen
    public void showCreateNewItemView()
    {
        setScreen(new AddItemScreen());
    }
    
    // adds product 
    public void addProduct(String name, float price, String url,int quantity,String essence,int mL,String gender,String model)
    {
        Perfume p = new Perfume(name, url, "price", "Unit price ($)", price, quantity,essence,mL,gender,model);
        if(getBackend().addPerfume(p) && addToPerfumes(p,0))
            showAdminView();
        else
            showPopup("The item is not created");
    }

    // receipt screen which shows old orders
    public void showReport()
    {
        setScreen(new ReceiptScreen());
    }
    //for alphabetical ordered screen
    public void showFilterView()
    {
        setScreen(new AlphabeticalScreen());
    }
    // type of current user
    public String getCurrentUserType()
    {
        return getBackend().getUserInfo(currentUserID).getType();
    }
    
    // search for items in given space of time
    public void searchPurchasedItems(String searchStart, String searchFinish)
    {
        int startDate = Integer.parseInt(searchStart);
        int endDate = Integer.parseInt(searchFinish);
        if(getBackend().searchPurchasedItems(startDate, endDate, currentUserID))
            showReport();
        else
            showPopup("No purchased items");
    }
}
