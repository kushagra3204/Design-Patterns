package CompositeDesignPattern;
import java.util.*;

abstract class VendingMachineComponent {
    String name;
    int price;
    
    public VendingMachineComponent(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    abstract int getPrice();
    abstract String getName();
}

class Item extends VendingMachineComponent {
    public Item(String name, int price) {
        super(name, price);
    }
    
    @Override
    int getPrice() {
        return price;
    }
    
    @Override
    String getName() {
        return name;
    }
}

class Composite extends VendingMachineComponent {
    List<VendingMachineComponent> components = new ArrayList<>();
    
    public Composite(String name, int price) {
        super(name, price);
    }
    
    void getItemsList() {
        int i=1;
        System.out.println("\nSnacks contents: ");
        System.out.printf("%-8s%8s\n","Items","Price");
        for (VendingMachineComponent component : components) {
            System.out.printf("%-8s%8s\n",component.getName(),component.price);
        }       
    }
    
    void addComponent(VendingMachineComponent component) {
        components.add(component);
    }
    
    @Override
    int getPrice() {
        int totalPrice = 0;
        for (VendingMachineComponent component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }
    
    @Override
    String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {
        Composite coke = new Composite("Coke", 75);
        coke.addComponent(new Item("Coke Can", 75));
        Composite pepsi = new Composite("Pepsi", 80);
        pepsi.addComponent(new Item("Pepsi Can", 80));
        Composite chips = new Composite("Chips", 25);
        chips.addComponent(new Item("Lays", 25));
        
        Composite snacks = new Composite("Snacks", 0);
        snacks.addComponent(coke);
        snacks.addComponent(pepsi);
        snacks.addComponent(chips);
        
        System.out.println("Snacks Name: " + snacks.getName());
        snacks.getItemsList();
        System.out.println("\nSnacks Price: " + snacks.getPrice());
        System.out.println("Dispensing...");
        System.out.println("Thank you for using!");
    }
}
