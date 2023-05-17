package BuilderDesignPattern;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main2 
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter the item: ");
        String item=in.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity=in.nextInt();
        VendingMachine2 vm = new Builder(item, 20).quantity(quantity).build();
        System.out.print("\n");
        System.out.println("Item: "+vm.getItem()+" | Quantity: "+vm.getQuantity()+" | Per Piece Price: "+vm.getPrice()/quantity);
        System.out.println("Total Price: "+vm.getPrice());
        System.out.println("Dispensing...");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thank you for using");
    }
}
