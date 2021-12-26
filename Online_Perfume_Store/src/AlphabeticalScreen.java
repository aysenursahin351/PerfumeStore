import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

 // class that print perfumes according to alphabetical order
public class AlphabeticalScreen extends Screen {
	  private static final long serialVersionUID = 1L;
	    
	    private JPanel scrollPanel;
	    
	    /**
		 *  Constructor.
		*/
	    public AlphabeticalScreen() {
	        setLayout(new BorderLayout(0, 0));
	        
	        JButton btnBack = new JButton("Back to old order");
	        btnBack.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                getController().showProductList();
	            }
	        });
	        
	        JPanel panel = new JPanel();
	        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
	        flowLayout.setAlignment(FlowLayout.RIGHT);
	        add(panel, BorderLayout.NORTH);
	        
	        JButton Filter = new JButton("Filter");
	        Filter.setBackground(Color.BLUE);
	        Filter.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                getController().showFilterView();
	            }
	        });
	        
	        JButton myInfoButton = new JButton("My account");
	        myInfoButton.setBackground(Color.ORANGE);
	        myInfoButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                UserDetailsScreen.display(getController());
	            }
	        });
	        
	        JButton cartButton = new JButton("Screen cart");
	        cartButton.setBackground(Color.RED);

	        cartButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	                getController().showCartView();
	            }
	        });
	        
	        
	        JButton createReport = new JButton("Purchased History");
	        createReport.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	                getController().showReport();
	            }
	        });
	        
	        JButton logoutButton = new JButton ("Log Out");
	        logoutButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e){
	                //getController().logout();
	                getController().init();
	            }
	        });
	        
	        panel.add(myInfoButton);
	        panel.add(createReport);
	        panel.add(cartButton);
	        panel.add(logoutButton);
	        panel.add(btnBack);
	        
	        scrollPanel = new JPanel();
	        JScrollPane scroll = new JScrollPane(scrollPanel);
	        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
	        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	        
	        add(scroll, BorderLayout.CENTER);

	    }

	    /**
		 * override Screen method and print perfumes according to alphabetical order
		 */
	    public void initialize() {
	        scrollPanel.removeAll();
	        List<Perfume> list = getController().getBackend().getPerfumes();
	        List <Perfume> list2 = list.stream().collect(Collectors.toList()); 
	        AlphabeticalOrder(list2);
	        for(Perfume p : list2){
	            if(p.getAmount() > 0)
	                scrollPanel.add(new PerfumeThumbnail(getController(), p));
	        }
	        revalidate();
	    }
	    public static void  AlphabeticalOrder(List<Perfume> perfumeList) {
			Collections.sort(perfumeList, new Comparator<Perfume>() {
	            @Override
	            public int compare(Perfume t1, Perfume t2) {
	                String s1 = t1.getName();
	                String s2 = t2.getName();
	                return s1.compareToIgnoreCase(s2);
	            }
	        });
	    }
	    
}
