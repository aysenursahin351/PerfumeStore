import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Dimension;
import java.util.Date;
import java.util.List;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;

//Print a screen that show past orders for customer and all orders for admin

public class ReceiptScreen extends Screen {
    
    private JPanel scrollPanel;
    private JTextField textField;
    static long findDifference(String start_date,
            String end_date)
{

 // SimpleDateFormat converts the
 // string format to date object
 SimpleDateFormat sdf
     = new SimpleDateFormat(
         "yyyy-MM-dd HH:mm:ss");
 long difference_In_Days = 0;
 long sum=0;
 
 try {

     // parse method is used to parse
     // the text from a string to
     // produce the date
     Date d1 = sdf.parse(start_date);
     Date d2 = sdf.parse(end_date);

     // Calucalte time difference
     // in milliseconds
     long difference_In_Time
         = d2.getTime() - d1.getTime();

     // Calucalte time difference in
     // seconds, minutes, hours, years,
     // and days
     long difference_In_Years
         = (difference_In_Time
            / (1000l * 60 * 60 * 24 * 365));
     sum=sum+difference_In_Years*365;
     difference_In_Days
         = (difference_In_Time
            / (1000 * 60 * 60 * 24))
           % 365;
      sum=sum+difference_In_Days;
     // Print the date difference in
     // years, in days, in hours, in
     // minutes, and in seconds

 	}
 	//Catch the Exception
 	catch (ParseException e) 
 	{
 		e.printStackTrace();
 	}
	
	return sum;
	}
    
    public ReceiptScreen() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 5, 5));
        add(topPanel, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        
        JButton myInfoButton = new JButton("Back to products");
        myInfoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if (getController().getCurrentUserType().equals("normal"))
                    getController().showProductList();
                if (getController().getCurrentUserType().equals("admin"))
                    getController().showAdminView();
            }
        });
        
        topPanel.add(panel);
        panel.add(myInfoButton);
        
        JPanel panel2 = new JPanel();
        FlowLayout flowLayout2 = (FlowLayout) panel.getLayout();
        flowLayout2.setAlignment(FlowLayout.LEFT);
        
        String years[] = new String[]{"2017", "2018", "2019", "2020", "2021"};
        JComboBox  year1 =new JComboBox(years);
        JComboBox  year2 =new JComboBox(years);
        
        String months[] = new String[12];
        for (int i = 0; i < months.length; i++)
        {
            months[i] = ((i<9)?"0":"") + (i+1);
        }
        JComboBox  month1 =new JComboBox(months);
        JComboBox  month2 =new JComboBox(months);
        
        String[] days = new String[31];        
        for (int i = 0; i < days.length; i++)
        {
            days[i] = ((i<9)?"0":"") + (i+1);
        }
        JComboBox  day1 =new JComboBox(days);
        JComboBox  day2 =new JComboBox(days);
        
        JLabel start = new JLabel("Start Date");
        
        
        JLabel finish = new JLabel("Finish Date");
        
        
        topPanel.add(panel2);
        panel2.add(start);       
        panel2.add(year1);
        panel2.add(month1);
        panel2.add(day1);
        
        panel2.add(finish);
        panel2.add(year2);
        panel2.add(month2);
        panel2.add(day2);
        
                
 
        
        
        

        //JComboBox  days=new JComboBox(day);
        
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //if ((String)month1.getSelectedItem().equals())
                getController().searchPurchasedItems((String)year1.getSelectedItem()
                        + (String)month1.getSelectedItem()+ 
                        (String)day1.getSelectedItem(), (String)year2.getSelectedItem()+
                         (String)month2.getSelectedItem()+ 
                        (String)day2.getSelectedItem());
            }
        });
        panel2.add(search);
        
        JButton searchAll = new JButton("Show All Purchased Items");
        searchAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().searchPurchasedItems("0","0");
            }
        });
        panel2.add(searchAll);
        
        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 10, 5, 5));
        
        
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        add(scroll, BorderLayout.CENTER);

    }

    //Override
    public void initialize() {
    	scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
    	if (getController().getCurrentUserType().equals("normal"))
        {
        scrollPanel.setLayout(new GridLayout(0, 8, 5, 5));
        }
        else
        	scrollPanel.setLayout(new GridLayout(0, 5, 5, 5));
    	
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        add(scroll, BorderLayout.CENTER);
        
        scrollPanel.removeAll();
        List<Receipt> records = getController().getBackend().getReceiptList();
        if (records.size() > 0)
        {
            scrollPanel.add(new JLabel("Order ID",JLabel.CENTER));
            scrollPanel.add(new JLabel("Item Name",JLabel.CENTER));
            scrollPanel.add(new JLabel("Quantity",JLabel.CENTER));
            scrollPanel.add(new JLabel("Total Price",JLabel.CENTER));
            scrollPanel.add(new JLabel("Purchased Time",JLabel.CENTER));
            
            String CurrentTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
            //If current user is a customer then complain and repayment buttons should appear
            //If current user is a admin doesnt show a button only past orders from customers
            if (getController().getCurrentUserType().equals("normal"))
            {
            	scrollPanel.add(new JLabel("Feedback",JLabel.CENTER));
            scrollPanel.add(new JLabel("Complain and press", JLabel.CENTER));
            scrollPanel.add(new JLabel("the correct button", JLabel.CENTER));
            
            }
            int counter = 0;
            for(Receipt r : records){
            	
                List<BasketItem> items = r.getItems().getList();
                for(int i = 0; i < items.size(); i++)
                {
                    if (i == 0)
                    {
                        scrollPanel.add(new JLabel((r.getOrderId() + ""), JLabel.CENTER));                  
                        scrollPanel.add(new JLabel(items.get(i).getPerfume().getName(), JLabel.CENTER));
                        scrollPanel.add(new JLabel((items.get(i).getQuantity() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel((r.getTotalPrice() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel(r.getPurchasedDate(), JLabel.CENTER));
                        JButton repaymentButton = new JButton("Repayment");
                  	  
                  	  
                      //If current user is a customer then complain and repayment buttons should appear
                        //If current user is a admin doesnt show a button only past orders from customers
                        if (getController().getCurrentUserType().equals("normal"))
                        {
                        	repaymentButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e) {
                                       String PurchasedTime=r.getPurchasedDate();
                                	
                                    if ( findDifference(PurchasedTime,CurrentTime)>15) {
                                    	getController().showPopup("It is not suitable for repayment conditions.");;
    								}
                                    else {
                                    	getController().showPopup("You can send your perfume to the contracted cargo.\nYour money has been returned to the card you have purchased from. \nThe refund process may vary from bank to bank.");
                                    	r.setSituation("iade");
                                    }
                                	

                                }

    							
                            });
                            scrollPanel.add(repaymentButton);
                        	textField = new JTextField();
                            
                            textField.setColumns(10);
                        JButton commentButton = new JButton("Complain for item");
                        int a = i;
                        commentButton.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                            	if (textField.getText().trim().equals("")) {
                           		 getController().showPopup("Write your complain to the field and press button next to item");
                           	}
                           	else {
                           		String s = e.getActionCommand();
                                   
                               	String text=textField.getText();
                               	Complain co = new Complain(items.get(a).getPerfume(),text);
                               	getController().getBackend().addComplains(co);
                          		 
                           	}
                            }
                        });
                        if (counter == records.size()-1 && i==items.size()-1)
                        {
                        	scrollPanel.add(textField);
                        	counter++;
                        }
                        else
                        {
                        	scrollPanel.add(new JLabel("", JLabel.CENTER));
                        }
                        scrollPanel.add(commentButton);
                        }
                    }
                    else
                    {
                        scrollPanel.add(new JLabel("", JLabel.CENTER));                  
                        scrollPanel.add(new JLabel(items.get(i).getPerfume().getName(), JLabel.CENTER));
                        scrollPanel.add(new JLabel((items.get(i).getQuantity() + ""), JLabel.CENTER));
                        scrollPanel.add(new JLabel("", JLabel.CENTER));
                        scrollPanel.add(new JLabel("", JLabel.CENTER));
                        
                      //If current user is a customer then complain and repayment buttons should appear
                        //If current user is a admin doesnt show a button only past orders from customers
                        if (getController().getCurrentUserType().equals("normal"))
                        {
                        	JButton repaymentButton = new JButton("Repayment");
                        	  
                        	  
                            repaymentButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e) {
                                       String PurchasedTime=r.getPurchasedDate();
                                	
                                    if ( findDifference(PurchasedTime,CurrentTime)>15) {
                                    	getController().showPopup("It is not suitable for repayment conditions.");
    								}
                                    else {
                                    	getController().showPopup("You can send your perfume to the contracted cargo.\nYour money has been returned to the card you have purchased from. \nThe refund process may vary from bank to bank.");
                                    	r.setSituation("iade");
                                    }
                                	

                                }

    							
                            });
                            scrollPanel.add(repaymentButton);
                        	textField = new JTextField();
                            
                            textField.setColumns(10);
                        JButton commentButton = new JButton("Complain for item");
                        int a = i;
                        commentButton.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e) {
                            	
                            	if (textField.getText().trim().equals("")) {
                           		 getController().showPopup("Write your complain to the field and press button next to item");
                           	}
                           	else {
                           		String s = e.getActionCommand();
                                   
                               	String text=textField.getText();
                               	
                               	Complain co = new Complain(items.get(a).getPerfume(),text);
                               	getController().getBackend().addComplains(co);
                          		 
                           	}
                                
                            }
                        });
                        
                        //Complain must be typed into last textfield so 
                        //for preventing frustration textfield appear only on last line
                        if (counter == records.size()-1 && i==items.size()-1)
                        {
                        	scrollPanel.add(textField);
                        	counter++;
                        }
                        else
                        {
                        	scrollPanel.add(new JLabel("", JLabel.CENTER));
                        }
                        scrollPanel.add(commentButton);
                        }
                    }
                }
                counter++;
            }
             
        }
        
        
        
        
        
        
        getController().getBackend().getReceiptList().clear();
        revalidate();
    }

}
