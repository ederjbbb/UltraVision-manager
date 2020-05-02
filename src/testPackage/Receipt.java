package testPackage;

import java.util.ArrayList;
import java.util.Collections;

public class Receipt {
    private String Item;
    private double price;




        // Bill object
    public Receipt(String item, double price) {
        Item = item;
        this.price = price;
    }

    public static void main(String[] args) {
        ArrayList<Receipt> receipts = new ArrayList<>();// Array to store objects
        double Bill = 0;// initial bill
        int itemsInTheTable =  receipts.size(); // get amount of items in the basket
        int totalPoints = 390;
        double points = totalPoints/100; // gets total discount in points
        // GreatestPrice methos

        double greatestPrice = 0; // to store greatest price base to be applied in case there are different prices in the list

        Receipt item1 = new Receipt("MAtrix", 6.50);
        Receipt item2 =  new Receipt("Spider Man", 3.00);
        Receipt item3 = new Receipt("Godfather", 2.90);
        Receipt item4 = new Receipt("Godfather", 1.90);
        Receipt item5 = new Receipt("Godfather", 4.90);
        Receipt item6 = new Receipt("Godfather", 3.90);
        Receipt item7 = new Receipt("Godfather", 2.90);
        Collections.addAll(receipts,item1,item2,item3,item4,item5,item6,item7); // add all objects basket array at once
        for (Receipt r : receipts
             ) { // loop over the array to get greatest price and store in the greatestprice variable
           if(r.price > greatestPrice){
               greatestPrice = r.price;// current greatest price


           }
            Bill += r.price; // bill without discount



        }
        double finalBill = Bill - greatestPrice;// final bill after discount
        System.out.println("Discount: "+greatestPrice);
        System.out.println("Bill :"+Bill);
        System.out.println("Final Bill ="+finalBill);


//  Applying discount to final Bill
    }
}
