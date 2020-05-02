package models;

public class RentalDataCustomer {
    private int loyaltyPoints;
    private int pendings;
    private String firstName;
    private String lastName;
    private String categoryPlan;
//    private Basket basket;

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getPendings() {
        return pendings;
    }

    public void setPendings(int pendings) {
        this.pendings = pendings;
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

    public String getCategoryPlan() {
        return categoryPlan;
    }

    public void setCategoryPlan(String categoryPlan) {
        this.categoryPlan = categoryPlan;
    }

    public RentalDataCustomer(int loyaltyPoints, int pendings, String firstName, String lastName, String categoryPlan) {
        this.loyaltyPoints = loyaltyPoints;
        this.pendings = pendings;
        this.firstName = firstName;
        this.lastName = lastName;
        this.categoryPlan = categoryPlan;
    }
}
