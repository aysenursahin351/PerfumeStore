

//main class
public class Main {

    
	public static void main(String[] args){
		
		Shop shopShop = new PerfumeShop();
		
		ShopController c = new ShopController(shopShop);
		c.init();
		
	}
	
}
