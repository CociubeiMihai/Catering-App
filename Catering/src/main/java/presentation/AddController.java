package presentation;

import business.BaseProduct;
import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddController {

    private AddView addView = new AddView();
    private DeliveryService deliveryService;

    public AddController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;

        LinkedList<MenuItem> menuItems = new LinkedList<>();

        addView.newItemListener(new NewItem(menuItems));
        addView.existingProductListener(new ExistingProduct(menuItems));
        addView.createListener(new CreateProduct(menuItems));
    }

    class NewItem implements ActionListener{

        LinkedList<MenuItem> menuItems;

        public NewItem(LinkedList<MenuItem> menuItems) {
            this.menuItems = menuItems;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            BaseProduct baseProduct = new BaseProduct(addView.getTitlu(),addView.getRating(),
                    addView.getCalories(),addView.getProteine(),addView.getFat(),
                    addView.getSodium(), addView.getPrice());
            menuItems.add(baseProduct);
            String[] s = new String[7];
            s[0] = baseProduct.getTitle();
            s[1] =String.valueOf(baseProduct.getRating());
            s[2] = String.valueOf(baseProduct.getCalories());
            s[3] = String.valueOf(baseProduct.getProtein());
            s[4] = String.valueOf(baseProduct.getFat());
            s[5] = String.valueOf(baseProduct.getSodium());
            s[6] = String.valueOf(baseProduct.getPrice());
            addView.addTable(s);
        }
    }

    class ExistingProduct implements ActionListener{

        LinkedList<MenuItem> menuItems;

        public ExistingProduct(LinkedList<MenuItem> menuItems) {
            this.menuItems = menuItems;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MenuItem m = deliveryService.gasesteElementul(addView.getTitlu());
            menuItems.add(m);
            String[] s = new String[7];
            s[0] = m.getTitle();
            s[1] =String.valueOf(m.getRating());
            s[2] = String.valueOf(m.getCalories());
            s[3] = String.valueOf(m.getProtein());
            s[4] = String.valueOf(m.getFat());
            s[5] = String.valueOf(m.getSodium());
            s[6] = String.valueOf(m.getPrice());
            addView.addTable(s);
        }
    }

    class CreateProduct implements ActionListener{

        LinkedList<MenuItem> menuItems;

        public CreateProduct(LinkedList<MenuItem> menuItems) {
            this.menuItems = menuItems;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            LinkedList<MenuItem> men = new LinkedList<>();
            for(MenuItem m: menuItems){
                men.add(m);
            }
            CompositeProduct compositeProduct = new CompositeProduct(addView.getTitlu(),men);
            deliveryService.addNewElement(compositeProduct);
            menuItems.clear();
            addView.removeOrder();
        }
    }

}
