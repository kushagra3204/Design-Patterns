package AdapterDesignPattern;
import java.util.HashMap;
import java.util.*;

interface VendingMachine {
    void dispenseItem(String item);
    double getItemCost(String item);
}

class VendingMachineAdapter implements VendingMachine {
    private Map<String, Double> items;
    public VendingMachineAdapter() {
        items = new HashMap<>();
        items.put("Coke", 30.0);
        items.put("Chips", 10.0);
        items.put("Candy", 10.0);
    }

    @Override
    public void dispenseItem(String item) {
        System.out.println("Dispensing " + item+"...");
    }

    @Override
    public double getItemCost(String item) {
        return items.getOrDefault(item, 0.0);
    }
}

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachineAdapter();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item to dispense: ");
        String item = scanner.nextLine();
        double cost = vendingMachine.getItemCost(item);
        if (cost == 0.0) {
            System.out.println("Item not found.");
        } else {
            System.out.println("Item cost: " + cost);
            vendingMachine.dispenseItem(item);
        }
    }
}