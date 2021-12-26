import java.util.ArrayList;
import java.util.List;

/**
 * This is a basket we use during sopping which we add BasketItems.
 */
public class Basket {

    private ArrayList<BasketItem> items = new ArrayList<>();
    
    /**
     * Takes quantity and item then adds to basket.     
     */
    public void add(Perfume p, float quantity){
        for(BasketItem ci : items){
            if(ci.perfume.equals(p)){
                ci.quantity += quantity;
                return;
            }
        }
        add(new BasketItem(p, quantity));
    }
    
    /**
     * Gives the quantity of specified item
     */
    public float getQuantity(Perfume p)
    {
       float quantity = 0;
       for(BasketItem ci : items){
            if(ci.perfume.equals(p))
                quantity = ci.quantity;
       }
       return quantity;
    }
    
    /**
     * Method that adds BasketItem to basket
     */
    public void add(BasketItem item){
        items.add(item);
    }
    
    /**
     * Gives a list that contain items in basket
     */
    public List<BasketItem> getList(){
        return items;
    }
    
    /**
     * Clear all items from basket
     */
    public void clear(){
        items.clear();
    }

    /**
     * Method that changes item in basket with different one 
     */
    public void setItems(ArrayList<BasketItem> items) {
        this.items = items;
    }
    
    /**
     * Choose an item to remove from Basket.
     */
    public void remove(BasketItem item){
        items.remove(item);
    }
    
}
