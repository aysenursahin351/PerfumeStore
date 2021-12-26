import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
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

//This is the class for admin to see if there is any complain abour perfumes

public class ComplainScreen extends Screen {
    
    private JPanel scrollPanel;
    
    // constructor.
     
    public ComplainScreen() {
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
        FlowLayout flowLayout2 = (FlowLayout) panel.getLayout();
        flowLayout2.setAlignment(FlowLayout.LEFT);
        
        
  
        
        scrollPanel = new JPanel();
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 2, 5, 5));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    //Override and run for this class
    public void initialize() {
        scrollPanel.removeAll();
        Queue coms = getController().getBackend().getComments();
        Queue temp = new Queue(9999);
        scrollPanel.add(new JLabel("Item Name",JLabel.CENTER));
        scrollPanel.add(new JLabel("Complain",JLabel.CENTER));
        if (coms.size()>0)
        {
           
            
            
            for(int i = 0 ;i<coms.size();i++){
            	Complain tempcomp = (Complain) coms.peek();
            	scrollPanel.add(new JLabel((tempcomp.getPerfume().getName()),JLabel.CENTER));
                scrollPanel.add(new JLabel(tempcomp.getComment(),JLabel.CENTER));
                getController().getBackend().addComplains((Complain)coms.dequeue());
               
            }
             
        }
        getController().getBackend().getReceiptList().clear();
        revalidate();
    }

}
