package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RentalDataTable {
    private SimpleIntegerProperty id;
    private SimpleStringProperty mediaType;
    private SimpleStringProperty titleName;
    private SimpleStringProperty genre;
    private SimpleStringProperty titleType;
    private SimpleDoubleProperty price;


    public RentalDataTable(int id, String mediaType, String titleName,
                           String genre, String titleType, double price) {
        this.id = new SimpleIntegerProperty(id);
        this.mediaType = new SimpleStringProperty(mediaType);
        this.titleName = new SimpleStringProperty(titleName);
        this.genre = new SimpleStringProperty(genre);
        this.titleType = new SimpleStringProperty(titleType);
        this.price = new SimpleDoubleProperty(price);
    }

    public int getId() {
        return id.get();
    }



    public void setId(int id) {
        this.id.set(id);
    }

    public String getMediaType() {
        return mediaType.get();
    }



    public void setMediaType(String mediaType) {
        this.mediaType.set(mediaType);
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

    public String getTitleType() {
        return titleType.get();
    }



    public void setTitleType(String titleType) {
        this.titleType.set(titleType);
    }

    public double getPrice() {
        return price.get();
    }



    public void setPrice(double price) {
        this.price.set(price);
    }


}
