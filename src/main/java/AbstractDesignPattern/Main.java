package AbstractDesignPattern;
interface Item {
    String name();
    Packing packing();
    float price();
}

interface Packing {
    String pack();
}

class Wrapper implements Packing {
    @Override
    public String pack() {
        return "Wrapper";
    }
}

class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}

abstract class VendingMachine {
    abstract Item item(String itemName);
}

class ColdDrinkVendingMachine extends VendingMachine {
    @Override
    Item item(String itemName) {
        if (itemName.equals("Coke")) {
            return new Coke();
        } else if (itemName.equals("Pepsi")) {
            return new Pepsi();
        }
        return null;
    }
}

class Coke implements Item {
    @Override
    public String name() {
        return "Coke";
    }
    @Override
    public Packing packing() {
        return new Bottle();
    }
    @Override
    public float price() {
        return 30.0f;
    }
}

class Pepsi implements Item {
    @Override
    public String name() {
        return "Pepsi";
    }
    @Override
    public Packing packing() {
        return new Bottle();
    }
    @Override
    public float price() {
        return 35.0f;
    }
}

class VendingMachineFactory {
    public static VendingMachine getVendingMachine(String type) {
        if (type.equals("colddrink")) {
            return new ColdDrinkVendingMachine();
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = VendingMachineFactory.getVendingMachine("colddrink");
        Item item = vendingMachine.item("Coke");
        System.out.println("Item: " + item.name());     
        System.out.println("Packing: " + item.packing().pack());
        System.out.println("Price: " + item.price());
    }
}