import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//This is the class customers see before confirm their orders
public class CheckoutPopup extends JDialog {

    private static final long serialVersionUID = 1L;

    
    public static void display(ShopController c){
        CheckoutPopup popup = new CheckoutPopup(c);
        popup.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        popup.setLocationRelativeTo(c.getWindow());
        popup.setVisible(true);
    }
    
    // constructor.
    
    public CheckoutPopup(ShopController c) {
        JDialog me = this;
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            getContentPane().add(panel, BorderLayout.CENTER);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JLabel lblNewLabel = new JLabel("ORDER DETAILS:");
                panel.add(lblNewLabel);
            }
            {
                JLabel spacer = new JLabel("  ");
                panel.add(spacer);
            }
            {
                String text = "<html>";
                for(BasketItem item : c.getBasket().getList()){
                    Basket thisItemInABasket = new Basket();
                    thisItemInABasket.add(item);
                    text += "ITEM: "+item.perfume.getName() +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QTY: " + item.quantity +
                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PRICE: " + c.getBackend().getPrice(thisItemInABasket) + "<br>";
                }
                
                //This is where we check if order value is more than 100 if it is program add a tester for customer.
                if(c.getTotalCartPrice()> 100.0)
                {
                	PerfumeShop d = new PerfumeShop();
                	d.ReadTester();
                	if (c.getBasket().getList().get(0).getPerfume().getGender().equals("W")) {
                		Random r=new Random();
                		int a=r.nextInt(6);
                		text +=  "You got free tester for shopping over 100$:"+ "<br>"+PerfumeShop.testerList.get(a-1).getName() +" "+ PerfumeShop.testerList.get(a-1).getModel() +"<br>";
					}
                	else if(c.getBasket().getList().get(0).getPerfume().getGender().equals("M")){
                		Random r=new Random();
                		int b=r.nextInt(6) + 4;
                		text += "You got free tester for shopping over 100$:"+ "<br>"+PerfumeShop.testerList.get(b).getName() +" "+ PerfumeShop.testerList.get(b).getModel() + "<br>";
                	}
                	
                }
                Customer currentCustomer = c.getCurrentCustomerDetails();
                text += "<br/>Name: " + currentCustomer.name + "<br/>"
                        + "Address: " + currentCustomer.address + "<br/>"
                        + "Phone Number: " + currentCustomer.phoneNumber 
                        + "</html>";
                JLabel details = new JLabel(text);
                panel.add(details);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton confirmButton = new JButton("Confirm order");
                confirmButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        c.attemptTransaction();
                        me.dispose();
                    }
                });
                confirmButton.setActionCommand("OK");
                buttonPane.add(confirmButton);
                getRootPane().setDefaultButton(confirmButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

}
