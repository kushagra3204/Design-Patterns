package DecoratorDesignPattern;
import java.util.*;
// The interface for the vending machine
interface VendingMachine {
    void selectItem();
    String getSelectedItem();
    void dispenseItem();
}

// The concrete implementation of the vending machine
class BasicVendingMachine implements VendingMachine {
    private String selectedItem; 
    HashMap<String,Integer> items=new HashMap<>();
    
    public void LoadItems() {
        items.put("coke",20);
        items.put("chips",10);
        items.put("candy",10);
    }
    
    public void selectItem() {
        LoadItems();
        System.out.println("Please select an item (coke, candy, or chips): ");
        Scanner scanner = new Scanner(System.in);
        selectedItem = scanner.nextLine();
    }
    
    public String getSelectedItem(){
        return selectedItem;
    }
    
    public void dispenseItem() {
        if(items.containsKey(selectedItem.toLowerCase())){
            System.out.println("Price: "+items.get(selectedItem));
            System.out.println("Dispensing " + selectedItem+"...");
        }
        else{
            System.out.println(selectedItem+" is not present in VendingMachine");
        }
    }
}

// The abstract decorator class
abstract class VendingMachineDecorator implements VendingMachine {
    private VendingMachine vendingMachine;
    

    public VendingMachineDecorator(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void selectItem() {
        vendingMachine.selectItem();
    }
    
    public String getSelectedItem(){
        return vendingMachine.getSelectedItem();
    }

    public void dispenseItem() {
        vendingMachine.dispenseItem();
    }
}

// The concrete decorator class for adding a confirmation step
class ConfirmationVendingMachine extends VendingMachineDecorator {
    public ConfirmationVendingMachine(VendingMachine vendingMachine) {
        super(vendingMachine);
    }

    public void selectItem() {
        super.selectItem();
        System.out.println("Are you sure you want to purchase " + super.getSelectedItem() + "? (y/n): ");
        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine();
        if (!confirmation.equals("y")) {
//            super.selectItem()= null;
            System.out.println("Purchase cancelled.");
        }
    }
}

// The main program
public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new BasicVendingMachine();
        vendingMachine = new ConfirmationVendingMachine(vendingMachine);
        
        vendingMachine.selectItem();
        vendingMachine.dispenseItem();
    }
}