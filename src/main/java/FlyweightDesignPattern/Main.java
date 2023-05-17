package FlyweightDesignPattern;
import java.util.*;

// Define the Item class to store the details of an item, such as name, price, and quantity
class Item {
    private String name;
    private double price;
    private int quantity;

    // Constructor to create an item with the given details
    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters to access and modify the details of the item
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

// Define the Flyweight Factory class to manage the objects of the Item class
class ItemFactory {
    // Use a hashmap to store the items and ensure that only one object of the same type is created and reused if needed
    private static Map<String, Item> items = new HashMap<String, Item>();

    // Method to get an item with the given name, price, and quantity
    public static Item getItem(String name, double price, int quantity) {
        Item item = items.get(name);

        // If the item doesn't exist in the hashmap, create a new one and add it to the hashmap
        if (item == null) {
            item = new Item(name, price, quantity);
            items.put(name, item);
        }

        return item;
    }
}

// Define the Vending Machine class to store an item and dispense it when requested
class VendingMachine {
    private Item item;
    private int quantity;

    // Constructor to create a vending machine with the given item details and quantity
    public VendingMachine(String name, double price, int quantity) {
        // Use the Flyweight Factory to get the item object with the given details
        this.item = ItemFactory.getItem(name, price, quantity);
        this.quantity = quantity;
    }
    
    public Item getItem()
    {
        return this.item;
    }

    // Method to select an item and dispense it if available
    public void selectItem() {
        // If the item is available in stock, display its details and dispense it
        if (quantity > 0) {
            System.out.println("Item selected: " + item.getName() + ", Price: " + item.getPrice());
            dispenseItem();
        }
        // If the item is not available in stock, display an "Out of stock" message
        else {
            System.out.println("Out of stock.");
        }
    }

    // Method to dispense an item and decrement its quantity
    private void dispenseItem() {
        quantity--;
        System.out.println("Item dispensed.");
    }
}

// Test the implementation by creating two vending machines with different items and quantities,
// taking input from the user to select an item, and dispensing it if available


public class Main {
    public static void main(String[] args) {
        // Create two vending machines with different items and quantities
        VendingMachine vendingMachine1 = new VendingMachine("Coke", 1.5, 2);
        VendingMachine vendingMachine2 = new VendingMachine("Pepsi", 1.25, 1);

        Scanner scanner = new Scanner(System.in);

        // Ask the user to select an item from the vending machine and dispense it if available
        while (true) {
            // Prompt the user to select a vending machine number
            System.out.print("Enter the number of the vending machine you want to use (1 or 2): ");
            
            // Get the user's input and validate it
            int vendingMachineNumber;
            try {
                vendingMachineNumber = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            
            // Get the selected vending machine object
            VendingMachine vendingMachine;
            if (vendingMachineNumber == 1) {
                vendingMachine = vendingMachine1;
            } else if (vendingMachineNumber == 2) {
                vendingMachine = vendingMachine2;
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;
            }

            // Prompt the user to select an item
            System.out.print("Enter the name of the item you want to purchase: ");
            String itemName = scanner.next();

            // Check if the selected item is available in the vending machine and dispense it if available
            Item item=vendingMachine.getItem();
            if (itemName.equalsIgnoreCase(item.getName())) {
                System.out.println("Dispensing " + item.getName() + ".");
            } else {
                System.out.println("Invalid item name or item out of stock. Please try again.");
            }
        }
    }
}