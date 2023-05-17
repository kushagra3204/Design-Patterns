package PrototypeDesignPattern;
public class Snack extends Item {
    public Snack(String itemName, double itemPrice, int itemQuantity) {
        super(itemName, itemPrice, itemQuantity);
    }
    
    @Override
    public void dispenseItem() {
        System.out.println("Dispensing " + getItemName()+"...");
    }
}
