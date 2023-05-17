package PrototypeDesignPattern;
import java.util.*;
public class Main3 {
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.ShowItems();
        System.out.print("Enter the Item code: ");
        String itemcode=in.nextLine();
        itemcode=itemcode.toUpperCase();
        System.out.print("Enter Quantity: ");
        int itemQuantity=in.nextInt();
        System.out.println();
        vendingMachine.dispenseItem(itemcode,itemQuantity);
    }
}