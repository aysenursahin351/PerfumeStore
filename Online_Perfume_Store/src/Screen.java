import javax.swing.JPanel;

public abstract class Screen extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ShopController controller;
	
	
	
	public void setController(ShopController c){
		this.controller = c;
	}

	//returns current controller operations 
	public ShopController getController(){
		return controller;
	}
	
	//Method for printing screens on gui
	public abstract void initialize();
}
