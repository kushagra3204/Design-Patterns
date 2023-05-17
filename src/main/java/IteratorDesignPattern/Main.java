package IteratorDesignPattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Item class representing items that can be vended
class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " -> Rs " + price;
    }
}

// VendingMachine class implementing Iterable<Item> interface using Iterator design pattern
class VendingMachine implements Iterable<Item> {
    private List<Item> items;

    public VendingMachine() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public Iterator<Item> iterator() {
        return new VendingMachineIterator();
    }

    // VendingMachineIterator class implementing Iterator<Item> interface
    private class VendingMachineIterator implements Iterator<Item> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < items.size();
        }

        @Override
        public Item next() {
            return items.get(currentIndex++);
        }
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addItem(new Item("Soda", 20));
        vendingMachine.addItem(new Item("Chips", 10));
        vendingMachine.addItem(new Item("Candy", 10));

        // Iterate through items in vending machine using Iterator design pattern
        for (Item item : vendingMachine) {
            System.out.println(item);
        }
    }
}
