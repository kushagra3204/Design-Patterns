package FactoryDesignPattern;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the item: ");
        String code=in.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity=in.nextInt();
        VendingMachine vm = Factory.createVendingMachine(code);
        if(vm!=null)
        {
            vm.setQuantity(quantity);
            System.out.print("\n");
            System.out.println("Item: "+vm.getItem()+" | Quantity: "+vm.getQuantity()+" | Per Piece Price: "+vm.getPrice()/quantity);
            System.out.println("Total Price: "+vm.getPrice());
            System.out.println("Dispensing...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            System.out.println("\nItem is not present!");
        }
        System.out.println("Thank you for using");
    }
}
