package models;

public class ReportRentalData {
    RentalDataBasket rentalDataBasket;
    RentalDataCustomer rentalDataCustomer;
    RentalDataTable rentalDataTable;
    private String firstName;
    private String lastName;
    private String  memberNumber;
    private String pickUpDate;
    private String returnDate;
    private double pointsUsed;
    private double pointsRemaining;
    private double amountChargedOnCard;
    private int pendings;
    private String receiptNumber;

    public ReportRentalData(String firstName, String lastName, String memberNumber,
                            String pickUpDate, String returnDate, double pointsUsed, double pointsRemaining,
                            double amountChargedOnCard, int pendings, String receiptNumber) {


        this.firstName = firstName;
        this.lastName = lastName;
        this.memberNumber = memberNumber;
        this.pickUpDate = pickUpDate;
        this.returnDate = returnDate;
        this.pointsUsed = pointsUsed;
        this.pointsRemaining = pointsRemaining;
        this.amountChargedOnCard = amountChargedOnCard;
        this.pendings = pendings;
        this.receiptNumber = receiptNumber;
        System.out.println("name :" +firstName );
        System.out.println("last name :" +lastName);
        System.out.println("PickUp Date : "+pickUpDate);
        System.out.println("Return Date :" + returnDate);
        System.out.println("points remaining :"+pointsRemaining);
        System.out.println("Charged :"+amountChargedOnCard+"Euros");
        System.out.println("Trasaction pending : "+ pendings);
        System.out.println("Receipt number :"+receiptNumber);
    }







    public void setRentalDataTable(RentalDataTable rentalDataTable) {
        this.rentalDataTable = rentalDataTable;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getPointsUsed() {
        return pointsUsed;
    }

    public void setPointsUsed(double pointsUsed) {
        this.pointsUsed = pointsUsed;
    }

    public double getPointsRemaining() {
        return pointsRemaining;
    }

    public void setPointsRemaining(double pointsRemaining) {
        this.pointsRemaining = pointsRemaining;
    }

    public double getAmountChargedOnCard() {
        return amountChargedOnCard;
    }

    public void setAmountChargedOnCard(double amountChargedOnCard) {
        this.amountChargedOnCard = amountChargedOnCard;
    }

    public int getPendings() {
        return pendings;
    }

    public void setPendings(int pendings) {
        this.pendings = pendings;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
}
