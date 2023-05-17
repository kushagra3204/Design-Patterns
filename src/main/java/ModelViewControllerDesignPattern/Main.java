package ModelViewControllerDesignPattern;
import java.util.*;

class Item {
    private String name;
    private int price;
    private int quantity;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


class VendingMachineModel {
    private List<Item> items;
    private int balance;

    public VendingMachineModel(List<Item> items) {
        this.items = items;
        this.balance = 0;
    }

    public void insertCoin(int amount) {
        this.balance += amount;
    }

    public void purchaseItem(Item item) {
        if (this.balance >= item.getPrice() && item.getQuantity() > 0) {
            item.setQuantity(item.getQuantity() - 1);
            this.balance -= item.getPrice();
        }
    }

    public List<Item> getItems() {
        return this.items;
    }

    public int getBalance() {
        return this.balance;
    }
}

class VendingMachineView {
    private Scanner scanner;

    public VendingMachineView() {
        this.scanner = new Scanner(System.in);
    }

    public int getSelection(List<Item> items) {
        System.out.println("Please select an item:");

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - Rs. " + items.get(i).getPrice());
        }

        return scanner.nextInt() - 1;
    }

    public int getCoins() {
        System.out.println("Please insert money: ");

        return scanner.nextInt();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}


class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineModel model;

    public VendingMachineController(VendingMachineView view, VendingMachineModel model) {
        this.view = view;
        this.model = model;
    }

    public void run() {
        while (true) {
            int selection = view.getSelection(model.getItems());

            if (selection < 0 || selection >= model.getItems().size()) {
                view.showMessage("Invalid selection. Please try again.");
                continue;
            }

            Item item = model.getItems().get(selection);

            if (item.getQuantity() == 0) {
                view.showMessage("Selected item is out of stock. Please select another item.");
                continue;
            }

            int coins = view.getCoins();

            if (coins < item.getPrice()) {
                view.showMessage("Insufficient money. Please insert more money.");
                continue;
            }

            int change = coins - item.getPrice();

            view.showMessage("Enjoy your " + item.getName() + "! Your change is Rs " + change + ".");

            item.setQuantity(item.getQuantity() - 1);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item("Soda", 20, 5));
        items.add(new Item("Chips", 10, 3));
        items.add(new Item("Candy", 10, 10));

        VendingMachineView view = new VendingMachineView();
        VendingMachineModel model = new VendingMachineModel(items);
        VendingMachineController controller = new VendingMachineController(view, model);

        controller.run();
    }
}