package FactoryDesignPattern;
public class Factory {
    public static VendingMachine createVendingMachine(String type) {
        if (type.equalsIgnoreCase("Soda")) {
            return new VendingMachine("Soda", 20, 1);
        } else if (type.equalsIgnoreCase("Chips")) {
            return new VendingMachine("Chips", 10, 1);
        } else if (type.equalsIgnoreCase("Candy")) {
            return new VendingMachine("Candy", 10, 1);
        } else {
            return null;
        }
    }
}