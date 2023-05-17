package ObserverDesignPattern;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

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
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class VendingMachineModel extends Observable {
    private List<Item> items;

    public VendingMachineModel(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void updateItemQuantity(int index, int quantity) {
        items.get(index).setQuantity(quantity);
        setChanged();
        notifyObservers(index);
    }
}

class VendingMachineView implements Observer {
    private Scanner scanner;

    public VendingMachineView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayItems(List<Item> items) {
        System.out.println("Available items:");

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName() + " - Rs. " + items.get(i).getPrice() + " (quantity: " + items.get(i).getQuantity() + ")");
        }
    }

    public int getSelection() {
        System.out.println("Please select an item:");

        return scanner.nextInt() - 1;
    }

    public int getCoins() {
        System.out.println("Please insert money:");

        return scanner.nextInt();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void update(Observable o, Object arg) {
        int index = (int) arg;
        Item item = ((VendingMachineModel) o).getItems().get(index);
        System.out.println("Item " + item.getName() + " has been restocked. New quantity: " + item.getQuantity());
    }
}

class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineModel model;

    public VendingMachineController(VendingMachineView view, VendingMachineModel model) {
        this.view = view;
        this.model = model;

        model.addObserver(view);
    }

    public void run() {
        while (true) {
            view.displayItems(model.getItems());

            int selection = view.getSelection();

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

            view.showMessage("Enjoy your " + item.getName() + "! Your change is Rs. " + change);

            item.setQuantity(item.getQuantity() - 1);

            if (item.getQuantity() == 0) {
                view.showMessage("Item " + item.getName() + " is out of stock.");
                model.updateItemQuantity(selection, 0);
            }
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
        VendingMachineController controller;
        controller = new VendingMachineController(view, model);
        controller.run();
    }
}