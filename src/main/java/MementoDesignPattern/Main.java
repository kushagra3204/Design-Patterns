package MementoDesignPattern;
import java.util.ArrayList;
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

// VendingMachine class implementing Memento design pattern
class VendingMachine {
    private List<Item> items;
    private double balance;

    public VendingMachine() {
        items = new ArrayList<>();
        balance = 0;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public double getBalance() {
        return balance;
    }

    // VendingMachineMemento class representing the state of the vending machine
    static class VendingMachineMemento {
        private final List<Item> items;
        private final double balance;

        public VendingMachineMemento(List<Item> items, double balance) {
            this.items = new ArrayList<>(items);
            this.balance = balance;
        }

        public List<Item> getItems() {
            return new ArrayList<>(items);
        }

        public double getBalance() {
            return balance;
        }
    }

    // Save current state of vending machine as a memento
    public VendingMachineMemento save() {
        return new VendingMachineMemento(items, balance);
    }

    // Restore vending machine state from a memento
    public void restore(VendingMachineMemento memento) {
        items = memento.getItems();
        balance = memento.getBalance();
    }

    // Buy an item from the vending machine
    public void buyItem(Item item, double payment) throws InsufficientFundsException {
        if (!items.contains(item)) {
            throw new IllegalArgumentException("Item not found");
        }
        if (payment < item.getPrice()) {
            throw new InsufficientFundsException(item.getPrice() - payment);
        }
        items.remove(item);
//        balance += item.getPrice();
    }

    // Exception class for insufficient funds error
    public static class InsufficientFundsException extends Exception {
        private double requiredAmount;

        public InsufficientFundsException(double requiredAmount) {
            this.requiredAmount = requiredAmount;
        }

        public double getRequiredAmount() {
            return requiredAmount;
        }
    }
}

// Example usage
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addItem(new Item("Candy", 10));
        vendingMachine.addItem(new Item("Chips", 10));
        vendingMachine.addItem(new Item("Soda", 20));
        VendingMachine.VendingMachineMemento memento = vendingMachine.save();
        
        System.out.println("Items available: " + vendingMachine.getItems());
        
        System.out.println("Items available: " + vendingMachine.getItems());
        try {
            
            // Buy an item and save vending machine state
            Item selectedItem = vendingMachine.getItems().get(2);
            double payment = 20;
            vendingMachine.buyItem(selectedItem, payment);
            System.out.println("Items available: " + vendingMachine.getItems());
            memento = vendingMachine.save();
            System.out.println("Items availablegjjj: " + vendingMachine.getItems());
            // Buy another item and restore vending machine state from previous memento
            selectedItem = vendingMachine.getItems().get(0);
            payment = 100;
            vendingMachine.buyItem(selectedItem, payment);
            System.out.println("Items availableu: " + vendingMachine.getItems().toString());
            System.out.println("Item bought: " + selectedItem.getName());
            System.out.println("Balance: Rs " + vendingMachine.getBalance());
            vendingMachine.restore(memento);
            vendingMachine.restore(memento);
            System.out.println("Items availableeeeeeeeee: " + vendingMachine.getItems());
            vendingMachine.restore(memento);
            System.out.println("Items available: " + vendingMachine.getItems());
            System.out.println("Restored to previous state:");
            System.out.println("Balance: Rs " + vendingMachine.getBalance());
            System.out.println("Items available: " + vendingMachine.getItems());
        } catch (VendingMachine.InsufficientFundsException e) {
            System.out.println("Insufficient funds. Required amount: Rs " + e.getRequiredAmount());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid item selected: " + e.getMessage());
        }
    }
}