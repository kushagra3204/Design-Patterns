package PrototypeDesignPattern;
public abstract class Item implements Cloneable {
    private String itemName;
    private double itemPrice;
    private int itemQuantity;
    
    public Item(String itemName, double itemPrice, int itemQuantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void dispenseItem() {
        System.out.println("Dispensing " + getItemName());
    }
    
    public double getItemPrice() {
        return itemPrice;
    }
    
    public int getItemQuantity() {
        return itemQuantity;
    }
    
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}