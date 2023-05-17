package StateDesignPattern;
import java.util.*;
interface VendingMachineState {
    void insertCoin(int amount);
    void selectItem(String item);
    void dispenseItem();
}

class NoCoinState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.setCurrentState(vendingMachine.getHasCoinState());
        vendingMachine.setAmount(amount);
        System.out.println("Rs. "+amount+" inserted");
    }

    @Override
    public void selectItem(String item) {
        System.out.println("Please insert a coin first");
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please insert a coin and select an item first");
    }
}

class HasCoinState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        vendingMachine.setAmount(vendingMachine.getAmount() + amount);
        System.out.println("Rs. "+amount+" inserted");
    }

    @Override
    public void selectItem(String item) {
        Item selectedItem = vendingMachine.getItem(item);
        if (selectedItem != null) {
            if (selectedItem.getPrice() <= vendingMachine.getAmount()) {
                vendingMachine.setCurrentState(vendingMachine.getSoldState());
                vendingMachine.setSelectedItem(selectedItem);
            } else {
                System.out.println("Insufficient funds");
            }
        } else {
            System.out.println("Invalid item selection");
        }
    }

    @Override
    public void dispenseItem() {
        System.out.println("Please select an item first");
    }
}

class SoldState implements VendingMachineState {
    private final VendingMachine vendingMachine;

    public SoldState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(int amount) {
        System.out.println("Please wait, item dispensing");
    }

    @Override
    public void selectItem(String item) {
        System.out.println("Please wait, item dispensing");
    }

    @Override
    public void dispenseItem() {
        Item selectedItem = vendingMachine.getSelectedItem();
        if (selectedItem != null) {
            int quantity = selectedItem.getQuantity();
            if (quantity > 0) {
                selectedItem.setQuantity(quantity - 1);
                System.out.println("Item dispensed: " + selectedItem.getName());
                if (vendingMachine.getAmount() > selectedItem.getPrice()) {
                    int change = vendingMachine.getAmount() - selectedItem.getPrice();
                    System.out.println("Change: Rs. " + change);
                }
                vendingMachine.setAmount(0);
                vendingMachine.setCurrentState(vendingMachine.getNoCoinState());
            } else {
                System.out.println("Out of stock");
                vendingMachine.setAmount(0);
                vendingMachine.setCurrentState(vendingMachine.getNoCoinState());
            }
        }
    }
}

class VendingMachine {
    private VendingMachineState noCoinState;
    private VendingMachineState hasCoinState;
    private VendingMachineState soldState;
    private VendingMachineState currentState;

    private int amount;
    private Item selectedItem;
    private Map<String, Item> items;

    public VendingMachine(Map<String, Item> items) {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        soldState = new SoldState(this);
        currentState = noCoinState;
        this.items = items;
    }
    public void insertCoin(int amount) {
        currentState.insertCoin(amount);
    }

    public void selectItem(String item) {
        currentState.selectItem(item);
    }

    public void dispenseItem() {
        currentState.dispenseItem();
    }

    public VendingMachineState getNoCoinState() {
        return noCoinState;
    }

    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }

    public VendingMachineState getSoldState() {
        return soldState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Item getItem(String name) {
        return items.get(name);
    }
}
    
class Item {
    private String name;
    private int price;
    private int quantity;
    public Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item("Coke", 20, 5);
        Item item2 = new Item("Pepsi", 10, 3);
        Item item3 = new Item("Sprite", 10, 4);
        Map<String, Item> items = new HashMap<>();
        items.put(item1.getName(), item1);
        items.put(item2.getName(), item2);
        items.put(item3.getName(), item3);

        VendingMachine vendingMachine = new VendingMachine(items);

        vendingMachine.insertCoin(25);
        vendingMachine.selectItem("Coke");
        vendingMachine.dispenseItem();
    }
}