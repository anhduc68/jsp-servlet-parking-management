
package business;

import java.util.Date;


public class Ticket {
    private int id;
    private float price;
    private Date expiryDate;
    private int isMonthTicket;
    private int isVipTicket;
    private Vehicle vehicle;
    private Vacant vacant;
    private User user;

    public Ticket() {
    }

    public Ticket(int id, float price, Date expiryDate, int isMonthTicket, int isVipTicket, Vehicle vehicle, Vacant vacant, User user) {
        this.id = id;
        this.price = price;
        this.expiryDate = expiryDate;
        this.isMonthTicket = isMonthTicket;
        this.isVipTicket = isVipTicket;
        this.vehicle = vehicle;
        this.vacant = vacant;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getIsMonthTicket() {
        return isMonthTicket;
    }

    public void setIsMonthTicket(int isMonthTicket) {
        this.isMonthTicket = isMonthTicket;
    }

    public int getIsVipTicket() {
        return isVipTicket;
    }

    public void setIsVipTicket(int isVipTicket) {
        this.isVipTicket = isVipTicket;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vacant getVacant() {
        return vacant;
    }

    public void setVacant(Vacant vacant) {
        this.vacant = vacant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
