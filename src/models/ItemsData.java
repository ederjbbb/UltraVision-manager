package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemsData {
    private SimpleIntegerProperty id;
    private SimpleStringProperty titleName;
    private  SimpleStringProperty genre;
    private  SimpleStringProperty director;
    private SimpleDoubleProperty price;
    private SimpleStringProperty year;
    private SimpleIntegerProperty totalQuantity;
    private SimpleIntegerProperty qty_available;
    private SimpleStringProperty mediaType;

    public int getQty_available() {
        return qty_available.get();
    }



    public void setQty_available(int qty_available) {
        this.qty_available.set(qty_available);
    }

    public ItemsData(int id, String titleName, String genre, String director, double price, String year, int total_qty,
                     String mediaType, int qty_available) {

        this.id = new SimpleIntegerProperty(id);
        this.qty_available = new SimpleIntegerProperty(qty_available);
        this.titleName = new SimpleStringProperty(titleName);
        this.genre = new SimpleStringProperty(genre);
        this.director = new SimpleStringProperty(director);
        this.price = new SimpleDoubleProperty(price);
        this.year = new SimpleStringProperty(year);
        this.totalQuantity = new SimpleIntegerProperty(total_qty);
        this.mediaType = new SimpleStringProperty(mediaType);
    }
    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitleName() {
        return titleName.get();
    }


    public void setTitleName(String titleName) {
        this.titleName.set(titleName);
    }

    public String getGenre() {
        return genre.get();
    }


    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getDirector() {
        return director.get();
    }


    public void setDirector(String director) {
        this.director.set(director);
    }

    public double getPrice() {

        return price.get();
    }


    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getYear() {
        return year.get();
    }


    public void setYear(String year) {
        this.year.set(year);
    }

    public int getTotalQuantity() {
        return totalQuantity.get();
    }


    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity.set(totalQuantity);
    }

    public String getMediaType() {
        return mediaType.get();
    }

    public void setMediaType(String mediaType) {
        this.mediaType.set(mediaType);
    }

}
