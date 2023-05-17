package FacadeDesignPattern;
import java.util.*;
public class VendingMachineFacade {
    private Inventory inventory;

    public VendingMachineFacade() {
        inventory = new Inventory();
    }

    public void displayItems() {
        List<Item> items = (List<Item>) inventory.getItems();
        System.out.println("Items available:");
        for (Item item : items) {
            System.out.printf("%-5s - Rs. %.2f\n",item.getName(),item.getPrice());
        }
    }

    public void dispenseItem(String itemName) {
        List<Item> items = (List<Item>) inventory.getItems();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.println("Dispensing " + item.getName() + "...");
                return;
            }
        }
        System.out.println("Item not found.");
    }
}
