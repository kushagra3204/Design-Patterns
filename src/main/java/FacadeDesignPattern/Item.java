package FacadeDesignPattern;

import java.util.*;

public class Item {
    private String name;
    private double price;
    
//    2 args constructor 
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
//    method for getting the name of item
    public String getName() {
        return name;
    }

//    method for getting the price of the item
    public double getPrice() {
        return price;
    }
}

class Inventory {
//    List for storing items in vending machine
    private List<Item> items;
    
//    constructor for intitializing the List
    public Inventory() {
        items = new ArrayList<Item>();
        items.add(new Item("Coke", 20));
        items.add(new Item("Chips", 10));
        items.add(new Item("Candy", 10));
    }

    public List<Item> getItems() {
        return items;
    }
}
