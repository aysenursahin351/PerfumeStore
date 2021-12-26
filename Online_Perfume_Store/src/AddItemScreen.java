import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
/**
 * This class print screen that where admins create new item with details
 */
public class AddItemScreen extends Screen
{
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton2_1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    
    /**
     * AddItemScreen constructor.
     */
    public AddItemScreen()
    {
        jPanel1 = new JPanel();
        jPanel1.setBackground(Color.PINK);
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jTextField2 = new JTextField();
        jLabel3 = new JLabel();
        jTextField3 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();

        
        jLabel1.setFont(new Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Perfume Name");

        jLabel2.setFont(new Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Perfume Price");

        jLabel3.setFont(new Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel3.setText("Perfume Image");

        
        
        JLabel lblPerfumeMl = new JLabel();
        lblPerfumeMl.setText("Perfume ml");
        lblPerfumeMl.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JLabel lblPerfumeGender = new JLabel();
        lblPerfumeGender.setText("Perfume Gender");
        lblPerfumeGender.setFont(new Font("Dialog", Font.BOLD, 14));
        
        JLabel lblPerfumeEssence = new JLabel();
        lblPerfumeEssence.setText("Perfume Essence");
        lblPerfumeEssence.setFont(new Font("Dialog", Font.BOLD, 14));
        
        jTextField4 = new JTextField();
        
        jTextField5 = new JTextField();
        
        jTextField6 = new JTextField();
        
        JLabel lblPerfumeModel = new JLabel();
        lblPerfumeModel.setText("Perfume Model");
        lblPerfumeModel.setFont(new Font("Dialog", Font.BOLD, 14));
        
        jTextField7 = new JTextField();
        
        JLabel lblPerfumeQuantity = new JLabel();
        lblPerfumeQuantity.setText("Perfume Quantity");
        lblPerfumeQuantity.setFont(new Font("Dialog", Font.BOLD, 14));
        
        jTextField8 = new JTextField();
        //Button
        jButton1.setText("Submit");
        jButton1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) 
            {
                if (jTextField1.getText().trim().equals("") || jTextField2.getText().trim().equals("") || jTextField3.getText().trim().equals(""))
                {
                   getController().showPopup("One or more required fields missing");
                }
                else
                {
                    getController().addProduct(jTextField1.getText(),
                    Float.parseFloat(jTextField2.getText()),jTextField3.getText(), Integer.parseInt(jTextField8.getText()), jTextField8.getText(),Integer.parseInt(jTextField4.getText()),jTextField5.getText(),jTextField7.getText());
                }
            }
        });
        //Button
        jButton2_1 = new JButton("Cancel");
        jButton2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getController().showAdminView();
            }
        });
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addComponent(jButton1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jButton2_1)
        					.addGap(72))
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addComponent(lblPerfumeQuantity, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(jTextField8))
        						.addGroup(jPanel1Layout.createSequentialGroup()
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        									.addComponent(jLabel3)
        									.addComponent(lblPerfumeMl, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
        									.addComponent(lblPerfumeModel, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
        									.addGroup(jPanel1Layout.createSequentialGroup()
        										.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        											.addComponent(lblPerfumeGender, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        											.addComponent(lblPerfumeEssence, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        										.addGap(18)))
        								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
        								.addComponent(jLabel1))
        							.addGap(18)
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
        									.addComponent(jTextField6, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        									.addComponent(jTextField5, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        									.addComponent(jTextField4, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        									.addComponent(jTextField2, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
        									.addComponent(jTextField3)
        									.addComponent(jTextField7)))))
        					.addGap(113))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel1)
        				.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel2))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jLabel3)
        				.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPerfumeMl, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPerfumeGender, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jTextField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(5)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPerfumeEssence, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jTextField6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblPerfumeModel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jTextField7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jTextField8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addGap(42)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jButton2_1)
        						.addComponent(jButton1)))
        				.addComponent(lblPerfumeQuantity, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
        			.addGap(19))
        );
        jPanel1.setLayout(jPanel1Layout);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 393, Short.MAX_VALUE))
        );
    }

    /**
	 * Override method from Screen Class
	 */
    public void initialize()
    {
    }
}
