package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReturnItemsAndHistory {
    private SimpleIntegerProperty id;
    private SimpleStringProperty titleName;
    private SimpleStringProperty pickupDate;
    private SimpleStringProperty returnDate;
    private SimpleDoubleProperty charges;
    private SimpleDoubleProperty pointsUsed;
    private SimpleDoubleProperty pointsRemaining;
    private SimpleIntegerProperty pendings;
    private SimpleStringProperty receiptNumber;
    private double gainedPoints;


    public ReturnItemsAndHistory(int id, String titleName, String pickupDate,
                                 String returnDate, double charges, double pointsUsed,
                                 double pointsRemaining, int pendings,
                                 String receiptNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.titleName = new SimpleStringProperty(titleName);
        this.pickupDate = new SimpleStringProperty(pickupDate);
        this.returnDate = new SimpleStringProperty(returnDate);
        this.charges = new SimpleDoubleProperty(charges);
        this.pointsUsed = new SimpleDoubleProperty(pointsUsed);
        this.pointsRemaining = new SimpleDoubleProperty(pointsRemaining);
        this.pendings = new SimpleIntegerProperty(pendings);
        this.receiptNumber = new SimpleStringProperty(receiptNumber);

    }
    public ReturnItemsAndHistory(int id, String titleName , String pickupDate,String returnDate){
        this.id = new SimpleIntegerProperty(id);
        this.titleName = new SimpleStringProperty(titleName);
        this.pickupDate = new SimpleStringProperty(pickupDate);
        this.returnDate = new SimpleStringProperty(returnDate);
    }

    public int getId() {
        return id.get();
    }

    public double getPointsUsed() {
        return pointsUsed.get();
    }
    public void setGainedPoints(double numberOfRents){
        this.gainedPoints = numberOfRents * 10;
    }
    public double getGainedPoints(){
        return gainedPoints;
    }


    public void setPointsUsed(int pointsUsed) {
        this.pointsUsed.set(pointsUsed);
    }

    public double getPointsRemaining() {
        return pointsRemaining.get();
    }



    public void setPointsRemaining(int pointsRemaining) {
        this.pointsRemaining.set(pointsRemaining);
    }

    public int getPendings() {
        return pendings.get();
    }

    public SimpleIntegerProperty pendingsProperty() {
        return pendings;
    }

    public void setPendings(int pendings) {
        this.pendings.set(pendings);
    }

    public String getReceiptNumber() {
        return receiptNumber.get();
    }

    public SimpleStringProperty receiptNumberProperty() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber.set(receiptNumber);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitleName() {
        return titleName.get();
    }

    public SimpleStringProperty titleNameProperty() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName.set(titleName);
    }

    public String getPickupDate() {
        return pickupDate.get();
    }

    public SimpleStringProperty pickupDateProperty() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate.set(pickupDate);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    public double getCharges() {
        return charges.get();
    }

    public SimpleDoubleProperty chargesProperty() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges.set(charges);
    }
}
