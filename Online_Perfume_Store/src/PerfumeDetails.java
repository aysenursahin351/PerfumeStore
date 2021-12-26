import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;


	// Popup for perfume details for customer
public class PerfumeDetails extends JDialog {

    private static final long serialVersionUID = 1L;
    
    private final JPanel contentPanel = new JPanel();
    JSpinner spinner;

    //caller for this popup
    public static void display(ShopController c, Perfume p){
        PerfumeDetails dialog = new PerfumeDetails(c, p);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLocationRelativeTo(c.getWindow());
        dialog.setVisible(true);
    }

    //Constructor
    public PerfumeDetails(ShopController c, Perfume p) {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                JLabel lblNewLabel_3 = new JLabel();
                lblNewLabel_3.setIcon(p.getImage());
                panel.add(lblNewLabel_3);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            {
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                panel_1.setBorder(null);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEFT);
                {
                    JLabel lblNewLabel_1 = new JLabel(p.getName()+" <br/>"+"Shop:"+p.getModel());
                    lblNewLabel_1.setText("<html>"+p.getName()+" <br/>"+p.getModel()+"</html>");

                    panel_1.add(lblNewLabel_1);
                    lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
                    lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
                }
            }
            {
            	
                JPanel panel_1 = new JPanel();
                panel.add(panel_1);
                FlowLayout fl_panel_1 = (FlowLayout) panel_1.getLayout();
                fl_panel_1.setAlignment(FlowLayout.LEADING);
                panel_1.setBorder(new EmptyBorder(1, 1, 1, 1));
                {
                    JLabel lblNewLabel_2 = new JLabel("<html></html>",SwingConstants.CENTER);
                    lblNewLabel_2.setText("<html>"+p.getPropertyDisplayName("price")+ ":" + p.getPropertyValue("price")+" <br/>Essence:"+p.getEssence()+" <br/>Gender:"+p.getGender()+" <br/>Size:"+p.getmL()+"mL</html>");
                    lblNewLabel_2.setFont( lblNewLabel_2.getFont().deriveFont(10f));
                    panel_1.add(lblNewLabel_2);
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JLabel lblNewLabel = new JLabel("Quantity:");
                buttonPane.add(lblNewLabel);
            }
            {
                spinner = new JSpinner();
                spinner.setModel(new SpinnerNumberModel(0, 0, p.getAmount(), 1));
                spinner.setPreferredSize(new Dimension(100, 30));
                buttonPane.add(spinner);
            }
            {
                JDialog me = this;
                JButton okButton = new JButton("Add to cart");
                okButton.setBackground(Color.ORANGE);
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                okButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                       
                       c.addToCart(p, Float.parseFloat(spinner.getValue() + ""));
                       me.dispose();
                    }
                });
                getRootPane().setDefaultButton(okButton);
            }
            {
                JDialog me = this;
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                cancelButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        me.dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }

}
}
