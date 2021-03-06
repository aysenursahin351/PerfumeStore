import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//The class that help us to print a screen for when a customer wants to see basket.
public class BasketScreen extends Screen {

    private static final long serialVersionUID = 1L;
    
    private JPanel scrollPanel;
    private JButton btnClear, btnCheckout;
    private JLabel lblNetTotal;

    // constructor.
    public BasketScreen() {

        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panel, BorderLayout.NORTH);
        
        JButton btnBack = new JButton("Back to products");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getController().showProductList();
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(btnBack);
      
        panel.setBackground(Color.pink);
        btnClear = new JButton("Remove all from cart");
        panel.add(btnClear);
        
        Component horizontalGlue = Box.createHorizontalGlue();
        panel.add(horizontalGlue);
        
        JLabel lblTotal = new JLabel("Total:");
        panel.add(lblTotal);
        
        lblNetTotal = new JLabel();
        panel.add(lblNetTotal);
        
        btnCheckout = new JButton("Checkout");
        panel.add(btnCheckout);

        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));
        scrollPanel.setLayout(new GridLayout(0, 1, 0, 1));
        
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);
        
    }

    // Override and run according to inputs come from other callers
    public void initialize() {
        
        scrollPanel.removeAll();
        
        for(BasketItem item : getController().getBasket().getList()){
            JPanel itemPanel = new JPanel();
            itemPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            itemPanel.setAlignmentX(LEFT_ALIGNMENT);
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setSize(new Dimension(10, 10));
            JPanel titlePanel = new JPanel();
            itemPanel.add(titlePanel);
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
            JLabel lblNewLabel_3 = new JLabel(item.perfume.getName());
            lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
            titlePanel.add(lblNewLabel_3);
            Component horizontalGlue_1 = Box.createHorizontalGlue();
            titlePanel.add(horizontalGlue_1);
            JPanel propertiesPanel = new JPanel();
            itemPanel.add(propertiesPanel);
            propertiesPanel.setLayout(new BoxLayout(propertiesPanel, BoxLayout.X_AXIS));
            JPanel quantityPanel = new JPanel();
            propertiesPanel.add(quantityPanel);
            quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.X_AXIS));
            JLabel lblQuantuty = new JLabel("Quantity: ");
            quantityPanel.add(lblQuantuty);
            JSpinner spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(item.quantity, 0, item.perfume.getAmount(), 1));
            quantityPanel.add(spinner);
            spinner.addChangeListener (new ChangeListener(){
                public void stateChanged(ChangeEvent e){
                    item.quantity = Float.valueOf(spinner.getValue().toString());
                    getController().showCartView();
                }
            });
            JLabel spacer_1 = new JLabel("          ");
            quantityPanel.add(spacer_1);
            JLabel lblNewLabel = new JLabel("Price ($):   ");
            quantityPanel.add(lblNewLabel);
            
            Basket thisItemInABasket = new Basket();
            thisItemInABasket.add(item);
            JLabel lblNewLabel_2 = new JLabel("$"+getController().getBackend().getPrice(thisItemInABasket));
            
            quantityPanel.add(lblNewLabel_2);
            JLabel label = new JLabel("          ");
            quantityPanel.add(label);
            
            JButton btnRemove = new JButton("Remove");
            btnRemove.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    getController().getBasket().remove(item);
                    getController().showCartView();
                }
            });
            propertiesPanel.add(btnRemove);
            
            Component horizontalGlue = Box.createHorizontalGlue();
            propertiesPanel.add(horizontalGlue);
            JSeparator separator = new JSeparator();
            itemPanel.add(separator);
            scrollPanel.add(itemPanel);
        }
        
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().getBasket().setItems(new ArrayList<BasketItem>());
                getController().showCartView();
            }
        });
        
        btnCheckout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	getController().checkWithAvaiable();
            }
        });
        
        lblNetTotal.setText("$"+getController().getTotalCartPrice());
        
        Component verticalGlue = Box.createVerticalGlue();
        scrollPanel.add(verticalGlue);
    }

}
