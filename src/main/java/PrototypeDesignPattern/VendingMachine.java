package PrototypeDesignPattern;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

class VendingMachine {
    private HashMap<String, Item> items;
    
    public VendingMachine() {
        items = new HashMap<String, Item>();
        loadVendingMachine();
    }
    
    private void loadVendingMachine() {
        Snack snack1 = new Snack("Chips", 10, 10);
        items.put("A1", snack1);
        Snack snack2 = new Snack("Candy", 10, 10);
        items.put("A2", snack2);
        Snack snack3 = new Snack("Soda", 20, 10);
        items.put("A3", snack3);
    }
    
    public Item getItem(String itemCode) {
        Item requestedItem = items.get(itemCode);
        return (Item)requestedItem.clone();
    }
    
    public void dispenseItem(String itemCode,int Quantity) {
        try{
            Item requestedItem = items.get(itemCode);
            requestedItem.setItemQuantity(requestedItem.getItemQuantity() - Quantity);
            if (requestedItem.getItemQuantity() >= 0) {
                System.out.println("Total cost: " + requestedItem.getItemPrice()*Quantity);
                System.out.println("Quantity: " + Quantity);
                requestedItem.dispenseItem();
            } else {
                System.out.println("Not enough quantity present!");
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(VendingMachine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        catch(Exception e)
        {
            System.out.println("Sorry, Item is not present in Vending Machine");
        }
        
        System.out.println("Thank you for using");
    }
    public void ShowItems()
    {
        System.out.println("List of items present in Vending Machine: ");
        System.out.println("Code -> Items -> Price -> Quantity Present");
        for (String key: items.keySet()){
            System.out.println(key +" -> "+items.get(key).getItemName()+" -> "+items.get(key).getItemPrice()+" -> "+items.get(key).getItemQuantity());
        }
        System.out.println("\n");
    }
}