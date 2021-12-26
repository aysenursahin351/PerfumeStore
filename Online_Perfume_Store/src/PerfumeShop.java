import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;


// The class that where program hold all data
public class PerfumeShop implements Shop {
	
	static ArrayList<Tester> testerList = new ArrayList<Tester>();
    ArrayList<Perfume> perfumeList = new ArrayList<Perfume>();
    HashMap<String, String> passwords = new HashMap<>();//Hash that holds password and name for control
    HashMap<String, Customer> details = new HashMap<>();//Hash that holds name and object of a customer
    ArrayList<Receipt> receiptList = new ArrayList<Receipt>();//
    Queue commentList = new Queue(9999);
    
    public PerfumeShop(){
        
    	//example admin and customers
        passwords.put("admin", "admin");
        details.put("admin", new Customer("adminke", "esref pasa cad", "12312", "2323","admin"));
        
        passwords.put("ayse", "ayse");
        details.put("ayse", new Customer("ayse", "abbey road street", "456", "234234","admin"));
        
        passwords.put("gizem", "gizem");
        details.put("gizem", new Customer("gizem", "hasanlı sokak", "786", "456","admin"));
        
        passwords.put("alper", "alper");
        details.put("alper", new Customer("alper", "Cavitpasa caddesi", "987654321", "5432775991","normal"));
        
        
      
        //txt reader which get predefined perfumes and holds in cache
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("product.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                String[] lineParts = line.split(",");	
                String name = lineParts[0];
                String url = lineParts[1];
                String type = lineParts[2];
                String displayName = lineParts[3];
                float price = Float.parseFloat(lineParts[4]);
                String sQuantity = lineParts[5];
                String essence = lineParts[6];
                String size = lineParts[7];
                String gender = lineParts[8];
                String model = lineParts[9];
                int ml = Integer.parseInt(size);
                int quantity = Integer.parseInt(sQuantity);
                Perfume birb = new Perfume(name);
                birb.setGender(gender);
                birb.setModel(model);
                birb.setmL(ml);
                birb.setAmount(quantity);
                birb.setProperty(type, displayName, price);
                birb.setEssence(essence);
                perfumeList.add(birb);
                perfumeList.get(i).setImage(url);
                i++;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //txt reader which get predefined testers and holds in cache
    public void ReadTester() {
    	BufferedReader reader;
    	   try {
    	        reader = new BufferedReader(new FileReader("tester.txt"));
    	        String line = reader.readLine();
    	        int j = 0;
    	        while (line != null) {
    	            String[] lineParts = line.split(",");	
    	            String name = lineParts[0];
    	            String url = lineParts[1];
    	            String type = lineParts[2];
    	            String displayName = lineParts[3];
    	            float price = Float.parseFloat(lineParts[4]);
    	            String sQuantity = lineParts[5];
    	            String essence = lineParts[6];
    	            String size = lineParts[7];
    	            String gender = lineParts[8];
    	            String model = lineParts[9];
    	            int ml = Integer.parseInt(size);
    	            int quantity = Integer.parseInt(sQuantity);
    	            Tester birb = new Tester(name);
    	            birb.setGender(gender);
    	            birb.setModel(model);
    	            birb.setmL(ml);
    	            birb.setAmount(quantity);
    	            birb.setProperty(type, displayName, price);
    	            birb.setEssence(essence);
    	            testerList.add(birb);
    	            testerList.get(j).setImage(url);
    	            j++;
    	            line = reader.readLine();
    	        }
    	        reader.close();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    }
    
    
    public List<Perfume> getPerfumes() {
        return perfumeList;
    }
    //Complain queue caller
    public  Queue getComments() {
        return commentList;
    }
    //Complain list updater
    public  void addComplains(Complain c) {
        commentList.enqueue(c);
   }
    public boolean login(String username, String password) {
        if(passwords.containsKey(username)){
            return passwords.get(username).equals(password);
        }
        return false;
    }

    public boolean signup(String username, String password) {
        if(passwords.containsKey(username)) return false;
        passwords.put(username, password);
        details.put(username, new Customer(username, "", "", "","normal"));
        return true;
    }

    public Customer getUserInfo(String username) {
        return details.get(username);
    }

    public boolean setUserInfo(String username, Customer info) {
        details.put(username, info);
        return true;
    }

    public float getPrice(Basket basket) {
        float total = 0;
        for(BasketItem item : basket.getList()) if(item.perfume.hasProperty("price")) total += ((float) item.perfume.getPropertyValue("price")) * item.quantity;
        return total;
    }

    public boolean processOrder(Customer customer, Basket basket, float total_price) {
        boolean success = true;
        if(!saveToFile(new Order(getMaxId(),customer, basket, total_price)) 
            || !changeAvailableAmount(basket))
            success = false;
        return success;
    }

    //When a checkout is confirmed this runs and add to records 
    public boolean saveToFile(Order order)
    {
        boolean flag = true;
        try
        {
            PrintWriter outputFile = new PrintWriter(new FileWriter("orders.txt",true));
            outputFile.print(order.getOrderId()+",");
            List<BasketItem> cartList = order.getBasket().getList();
            for (int index = 0; index < cartList.size(); index++)
            {
                outputFile.print(cartList.get(index).getPerfume().getName()+",");
                outputFile.print(cartList.get(index).getPerfume().getPropertyValue("price")+",");
                outputFile.print(cartList.get(index).getQuantity()+",");
                outputFile.print(cartList.get(index).getPerfume().getEssence()+",");
                outputFile.print(cartList.get(index).getPerfume().getmL()+",");
                outputFile.print(cartList.get(index).getPerfume().getGender()+",");
                outputFile.print(cartList.get(index).getPerfume().getModel()+",");

            }
            outputFile.print(order.getTotalPrice()+",");
            outputFile.print(order.getCustomer().name + "," +
                                order.getCustomer().address + ","
                                + order.getCustomer().cardNumber+ ","
                                + order.getCustomer().phoneNumber + ",");
            outputFile.println(order.getPurchasedTime());
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Unexpected I/O error occured");
            flag = false;
        }
        return flag;
    }
    //Returns the number of orders that orders.txt contains
    public int getMaxId()
    {        
        ArrayList<String> orderList = new ArrayList<String>(); 
        try
        {
            FileReader inputFile = new FileReader("orders.txt");
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                orderList.add(parser.nextLine());
            }
            inputFile.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Have not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return orderList.size();
    }
    
    //If admin adds a new item it will be added to text file as well
    public boolean saveToProductFile()
    {
        boolean flag = true;
        try
        {
            PrintWriter outputFile = new PrintWriter(new FileWriter("product.txt",false));  
            for (int index = 0; index < perfumeList.size(); index++)
            {
                outputFile.print(perfumeList.get(index).getName());
                outputFile.print("," + perfumeList.get(index).getUrl());
                for(String key : perfumeList.get(index).getProps().keySet())
                    outputFile.print("," + key + 
                        "," + perfumeList.get(index).getPropertyDisplayName(key) + 
                        "," + perfumeList.get(index).getPropertyValue(key));
                outputFile.print("," + perfumeList.get(index).getAmount());
                outputFile.print("," + perfumeList.get(index).getEssence());
                outputFile.print("," + perfumeList.get(index).getmL());
                outputFile.print("," + perfumeList.get(index).getGender());
                outputFile.print("," + perfumeList.get(index).getModel());

                outputFile.println();
            }
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Unexpected I/O error occured");
            flag = false;
        }
        return flag;
    }
    
    
    public boolean addPerfume(Perfume p)
    {
        perfumeList.add(p);
        return true;
    }
   
    
    public boolean addToPerfumes(Perfume p, int quantity)
    {
        p.setAmount(quantity);
        if(saveToProductFile())
            return true;
        else
            return false;
    }
    
    public boolean changeAvailableAmount(Basket basket)
    {
        boolean flag = false;
        for (int i=0; i < basket.getList().size(); i++)
        {
            BasketItem item = basket.getList().get(i);
            for (int j=0; j < perfumeList.size(); j++)
                if(perfumeList.get(j).getName().equals(item.getPerfume().getName()))
                    perfumeList.get(j).setAmount(0-(int)item.getQuantity());
        }
        if(saveToProductFile())
            flag = true;
        return flag;
    }
    
    
    //This runs and checks if tehre is was an order in given space of time
    public boolean searchPurchasedItems(int startDate, int endDate, String currentUserID)
    {
       
       boolean hasPurchasedItems = false;
        try
        {
            FileReader readProd = new FileReader("orders.txt");
            Scanner parser = new Scanner(readProd);
            while(parser.hasNextLine())
            {
                String prodLine = parser.nextLine();
                String[] splittedValues = prodLine.split(",");
                //System.out.println(splittedValues.length);
                int orderId = Integer.parseInt(splittedValues[0]);
                String purchasedTime = splittedValues[splittedValues.length-1];
                String phoneNum = splittedValues[splittedValues.length-2];
                String cardNum = splittedValues[splittedValues.length-3];
                String address = splittedValues[splittedValues.length-4];
                String customerName = splittedValues[splittedValues.length-5];
                float totalPrice = Float.parseFloat(splittedValues[splittedValues.length-6]);
                int purDate = splitDateAndTime(purchasedTime);
                
                if ((getUserInfo(currentUserID).getType().equals("admin") && startDate == 0 && endDate == 0)||
                    (getUserInfo(currentUserID).getType().equals("admin") && compareIntDate(purDate, startDate, endDate))||
                    (startDate == 0 && endDate == 0 && customerName.equals(currentUserID)) ||
                    (compareIntDate(purDate, startDate, endDate) && customerName.equals(currentUserID)))
                {
                    Customer c = new Customer(customerName, address, cardNum, phoneNum, "normal");
                    Basket items = new Basket();
                    for (int i=1; i<splittedValues.length-6; i+=7)
                    {
                    	
                        items.add(new Perfume(splittedValues[i],splittedValues[i+1]),Float.parseFloat(splittedValues[i+2]));
                    }
                
                    Receipt p = new Receipt(orderId, c, items, totalPrice, purchasedTime);
                    receiptList.add(p);
                    hasPurchasedItems = true;
                }      
            }
            readProd.close();
       }
        catch (FileNotFoundException exception)
        {
            System.out.println("Have not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return hasPurchasedItems;
    }
    
    public ArrayList<Receipt> getReceiptList()
    {
        return receiptList;
    }
     
    public int splitDateAndTime(String sth)
    {
        String[] splittedValues = sth.split("\\s+");
        String dateField = splittedValues[0];
        String timeField = splittedValues[1];
        return dateModify(dateField);
    }
    
    public int dateModify(String date)
    {
        String[] splittedDate = date.split("-");
        String year = splittedDate[0];
        String month = splittedDate[1];
        String day = splittedDate[2];
        String newDate = year + month + day;
        int comDate = Integer.parseInt(newDate);
        return comDate;
        //System.out.println(comDate);
    }
    
    public boolean compareIntDate(int comDate, int startDate, int endDate)
    {
        if (comDate >= startDate && comDate <= endDate)
        {
            return true;
        }
        return false;
    }

	
}
