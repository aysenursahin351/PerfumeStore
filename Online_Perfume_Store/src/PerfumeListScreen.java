import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

//This is the class that creates a field for PerfumeThumbnail to fill and show perfumes to customer
//for customer
public class PerfumeListScreen extends Screen {
    
    private static final long serialVersionUID = 1L;
    
    private JPanel scrollPanel;
    
    
    public PerfumeListScreen() {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBorder(getBorder());
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel, BorderLayout.NORTH);
        
        JButton myInfoButton = new JButton("My account");
        myInfoButton.setBackground(Color.CYAN);
       // myInfoButton.setBounds(0, 500, 100, 100);
        Dimension size = myInfoButton.getPreferredSize();
        myInfoButton.setBounds(10, 10, size.width, size.height);
        myInfoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                UserDetailsScreen.display(getController());
            }
        });
        
        JButton logoutButton = new JButton ("Log Out");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().logout();
                getController().init();
            }
        });
        
        
        JButton createReport = new JButton("Purchased History");
        createReport.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                getController().showReport();
            }
        });
        
        JButton cartButton = new JButton("Show basket");
        cartButton.setBackground(Color.MAGENTA);
        cartButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCartView();
            }
        });
        panel.add(cartButton);
        panel.add(createReport);
        panel.add(myInfoButton);
        JButton Filter = new JButton("Filter");
        Filter.setBackground(Color.blue);
        Filter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showFilterView();
            }
        });
        panel.add(Filter);
        panel.add(logoutButton);
        
        scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.PINK);
        scrollPanel.setForeground(Color.YELLOW);
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);

    }

    // Override
    public void initialize() {
        scrollPanel.removeAll();
        List<Perfume> list = getController().getBackend().getPerfumes();
        for(Perfume p : list){
            if(p.getAmount() > 0)
                scrollPanel.add(new PerfumeThumbnail(getController(), p));
        }
        revalidate();
    }

}
