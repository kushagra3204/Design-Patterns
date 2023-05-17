package BuilderDesignPattern;

public class VendingMachine2
{
    private String item;
    private int price;
    private int quantity;
    
    VendingMachine2(Builder builder) 
    {
        this.item = builder.item;
        this.price = builder.price;
        this.quantity = builder.quantity;
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