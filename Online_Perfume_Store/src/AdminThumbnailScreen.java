import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

/**
 
 * There are two different thumbnail type this one is for admin view which contains 
 * add more perfume to stock function. 
 
 */
public class AdminThumbnailScreen extends JPanel
{
    /**
     
     * Constructor 
     
     */
    public AdminThumbnailScreen(ShopController c, Perfume p)
    {
    	setBackground(Color.PINK);
        setBorder(new EmptyBorder(9, 3, 10, 10));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));
		
		JLabel title = new JLabel(p.getName());
		panel_2.add(title);
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setBackground(Color.BLACK);
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel imgLabel = new JLabel();
		imgLabel.setIcon(p.getImage());
		panel_2.add(imgLabel);
		JButton addOne = new JButton("Add perfume");
		addOne.setBackground(Color.PINK);
		add(addOne);
		addOne.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				c.addToPerfumes(p, 1);
			}
		});
		
		
		JButton view = new JButton("Details");
		view.setBackground(Color.CYAN);
		view.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		view.setHorizontalAlignment(SwingConstants.RIGHT);
		add(view);
		view.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				PerfumeDetailsAdmin.display(c, p);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.PINK);
		add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
    }
}
