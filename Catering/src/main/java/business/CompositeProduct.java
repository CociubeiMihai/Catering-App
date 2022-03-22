package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class CompositeProduct extends MenuItem implements Serializable {

    private LinkedList<MenuItem> menuItems = new LinkedList<>();

    public CompositeProduct(String title, LinkedList<MenuItem> menuItems){
        super(title);
        int  calories = 0,protein = 0,fat = 0,sodium = 0,price = 0;
        float rating = 0;
        this.menuItems = menuItems;
        for( MenuItem m: menuItems){
            rating += m.getRating();
            calories += m.getCalories();
            protein += m.getProtein();
            sodium += m.getSodium();
        }
        rating = rating / menuItems.size();
        setRating(rating);
        setCalories(calories);
        setPrice(computePrice());
        setCalories(calories);
        setProtein(protein);
        setSodium(sodium);

    }

    @Override
    public int computePrice() {
        int total = 0;
        for(MenuItem m : menuItems){
            total += ((BaseProduct)m).computePrice();
        }
        setPrice(total);
        return total;
    }

}
