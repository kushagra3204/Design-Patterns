package SingletonDesignPattern;
import java.util.*;
class VendingMachine 
{
//     instance variable to hold the single instance of the class
    private static VendingMachine instance = null;
    private static final Object lock = new Object();
    private Map<String, Integer> itemsAndPrices;
    

    private VendingMachine() 
    {
        itemsAndPrices = new HashMap<>();        //Hashmap for storing items in vending machine using hashmap
        itemsAndPrices.put("chips", 50);
        itemsAndPrices.put("candy", 60);
        itemsAndPrices.put("cold drink", 70);
    }

//   method to get the single instance of the class
    public static VendingMachine getInstance() 
    {
        if (instance == null) 
        {
            synchronized (lock) 
            {
                if (instance == null)
                {
                    instance = new VendingMachine();
                }
            }
        }
        return instance;
    }
    
//    method to get price of the item (Can be used to calculate total price if more than one item is dispensed)
    public int getPrice(String item) 
    {
        if(itemsAndPrices.containsKey(item)) 
        {
            return itemsAndPrices.get(item);
        }
        return -1;
    }

    //method to dispense the items
    public void dispenseItem(String item) 
    {
        if (itemsAndPrices.containsKey(item))
        {
            System.out.println("Dispensing " + item+"...");
        }
        else 
        {
            System.out.println("Item not available");
        }
    }
//    method for showing items present in vending machine with their price
    void ShowMenu()
    {
        System.out.println("List of items:\n");
        int c=1;
        System.out.printf("%-15s%-15s\n","Item","Prices");
        for (Map.Entry<String, Integer> entry : itemsAndPrices.entrySet()) 
        {
            System.out.printf("%-15s%-15s\n",entry.getKey(),entry.getValue());
        }
    }
}


//main class
public class Main
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in); //Scanner declaration to take input from user
        VendingMachine vm = VendingMachine.getInstance();  //creating object of the class VendingMachine
        vm.ShowMenu();   //calling method to show menu
        System.out.println("Choose from the above options: ");
        System.out.print("Enter your choice: ");
        String choice=in.nextLine().toLowerCase(); //to convert entered choice of user to lowercase
        vm.dispenseItem(choice);  //calling method dispenseItem to dispense the item that user want
        
    }
}