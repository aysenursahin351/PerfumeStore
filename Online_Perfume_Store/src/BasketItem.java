/**
 * Class for perfumes added to basket before checkout
 */
public class BasketItem {

    //Perfume
     
    public Perfume perfume;
    
    // The quantity
     
    public float quantity;

    /**Constructor.
     */
    public BasketItem(Perfume perfume, float quantity) {
        this.perfume = perfume;
        this.quantity = quantity;
    }
    
    // Increase quantity by 1 .
     
    public void add(){
        add(1);
    }
    
    //Increase quantity depending on how many you add
     
    public void add(float quantity){
        this.quantity += quantity;
    }
    
    
    public float getQuantity(){
       return quantity;
    }
    
    public Perfume getPerfume()
    {
        return perfume;
    }
}
