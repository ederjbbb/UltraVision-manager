package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RentalDataBasket {
    private SimpleStringProperty titleName;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty quantity;

    public RentalDataBasket(String titleName, Double price,Integer quantity) {
        this.titleName = new SimpleStringProperty(titleName);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);

    }

    public String getTitleName() {
        return titleName.get();
    }

    public SimpleStringProperty titleNameProperty() {
        return titleName;
    }

    public int getQuantity() {
        return quantity.get();
    }



    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }





    public void setTitleName(String titleName) {
        this.titleName.set(titleName);
    }

    public double getPrice() {
        return price.get();
    }




    public void setPrice(double price) {
        this.price.set(price);
    }
}
