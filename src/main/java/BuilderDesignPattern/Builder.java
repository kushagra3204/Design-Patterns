package BuilderDesignPattern;
public class Builder 
{
    String item;
    int price;
    int quantity;

    public Builder(String item, int price) {
        this.item = item;
        this.price = price;
    }

    public Builder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public VendingMachine2 build() {
        return new VendingMachine2(this);
    }
}
