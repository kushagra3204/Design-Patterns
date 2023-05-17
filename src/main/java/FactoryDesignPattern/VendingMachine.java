package FactoryDesignPattern;

public class VendingMachine {
    private String item;
    private int price;
    private int quantity;

    public VendingMachine(String item, int price, int quantity) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }
    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }
    public String getItem() {
        return item;
    }
    public int getPrice() {
        return price*quantity;
    }
    public int getQuantity() {
        return quantity;
    }
}