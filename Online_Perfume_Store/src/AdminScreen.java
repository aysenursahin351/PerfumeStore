import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
/**
 * if the user is admin this class runs and show admin screen which contains 
 * receipt view and add item buttons
 */
public class AdminScreen extends Screen
{
    private JPanel scrollPanel;

    /**
     * Constructor

     */
    public AdminScreen()
    {
        setLayout(new BorderLayout(0, 0));
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        add(panel, BorderLayout.NORTH);
        
        JButton createReport = new JButton("Create Receipt");
        createReport.setBackground(Color.WHITE);
        createReport.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().createReportForAdmin();
                getController().showReport();
            }
        });
        
        JButton createNewItem = new JButton("Create New Item");
        createNewItem.setBackground(Color.MAGENTA);
        createNewItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                getController().showCreateNewItemView();
            }
        });
        
      
        
        JButton logoutButton = new JButton ("Log Out");
        logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //getController().logout();
                getController().init();
            }
        });
        
        JButton comments = new JButton("Comments");
        comments.setBackground(Color.CYAN);
        comments.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	getController().showComments();
            }
        });
        panel.add(comments);
        
        panel.add(createReport);
        panel.add(createNewItem);
        panel.add(logoutButton);
        //panel.add(Unlike);
        scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.PINK);
        JScrollPane scroll = new JScrollPane(scrollPanel);
        scrollPanel.setLayout(new GridLayout(0, 3, 0, 0));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scroll, BorderLayout.CENTER);
    }

   
    
    ///override Screen class method and print perfumes for admin view
    public void initialize() {
        scrollPanel.removeAll();
        List<Perfume> list = getController().getBackend().getPerfumes();
        for(Perfume p : list){
            scrollPanel.add(new AdminThumbnailScreen(getController(), p));
        }
        revalidate();
    }
}
