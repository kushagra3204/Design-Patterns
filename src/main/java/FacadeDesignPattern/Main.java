package FacadeDesignPattern;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        VendingMachineFacade facade = new VendingMachineFacade();
        //Displaying items present in the vending machine
        facade.displayItems();
        
        //takes input from user what item user wants from vending machine
        System.out.print("\nEnter item you want: ");
        String item=in.nextLine();
        facade.dispenseItem(item);
        
        //time logger for creating a time delay to give a experience of machine which takes times to dispense item
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thank you for using");
    }
}