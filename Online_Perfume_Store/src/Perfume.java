import java.util.HashMap;

import javax.swing.ImageIcon;

// Creates an object which holds data for a perfume 

public class Perfume {

	private HashMap<String, Object> props = new HashMap<String, Object>();
	private HashMap<String, String> dispNames = new HashMap<String, String>();
	//variables for perfume
	private String name;
	private ImageIcon image = null;
	private int amount = 0;
	private String url = null;
	private String essence;
	private int mL=0;
	private String gender;
	private String model;
	
	//Constructor
	public Perfume(String name){
		setName(name);
	}
	
	//Constructor for full information
	public Perfume(String name, String url, String id, String displayName, Object value, int amount,String essence,int mL,String gender,String model)
	{
	    setName(name);
	    setImage(url);
	    setProperty(id, displayName, value);
	    setAmount(amount);
	    setmL(mL);
	    setGender(gender);
	    setModel( model);
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getEssence() {
		return essence;
	}

	public void setEssence(String essence) {
		this.essence = essence;
	}
	
	public int getmL() {
		return mL;
	}

	public void setmL(int mL) {
		this.mL = mL;
	}
	
	//Constructor for property
	public Perfume(String name, Object value)
	{
	    setName(name);
	    setProperty("unitprice", "unitprice", value);
	    //setAmount(amount);
	}
	
	// gets and resize image from url
	public void setImage(String url){
		this.image = ShopController.generateIcon(url, 100, 100);//resize
		this.url = url;
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public void setProperty(String id, String displayName, Object value){
		this.props.put(id, value);
		this.dispNames.put(id, displayName);
	}
	
	// Gives the value property.
	 
	public Object getPropertyValue(String id){
		return props.get(id);
	}
	
	
	public String getPropertyDisplayName(String id){
		return dispNames.get(id);
	}
	
	
	public String getPropertiesAsText(){
		String out = "<html>";
		for(String key : this.props.keySet()) 
		out += (getPropertyDisplayName(key) + ": " + getPropertyValue(key).toString()) + "<br/>";
		out += "</html>";
		return out;
	}
	
	
	public boolean hasProperty(String id){
		return props.containsKey(id);
	}
	
	
	public ImageIcon getImage(){
		if(this.image == null) return ShopController.NO_IMAGE_ICON;
		else return this.image;
	}
	
	
	public void setAmount(int changeQuantity)
	{
	    amount += changeQuantity;
	}
	
	
	
	public int getAmount()
	{
		return amount;
	}
	
	
	public HashMap<String, Object> getProps()
	{
	    return props;
	}
	
	
	public String getUrl()
	{
	    return url;
	}
}
