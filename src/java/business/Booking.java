
package business;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Booking {
    private int id;
    private Date checkin;
    private Date checkout;
    private float price;
    private int isCheckin;
    private int isCheckout;
    
    private Vehicle vehicle;
    private Vacant vacant;
    private User user;
    private Ticket ticket;
    private String checkinFormat;
    private String checkoutFormat;
    public Booking() {
    }

    public Booking(int id, Date checkin, Date checkout, float price, int isCheckin, int isCheckout, Vehicle vehicle, Vacant vacant, User user, Ticket ticket) {
        this.id = id;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
        this.isCheckin = isCheckin;
        this.isCheckout = isCheckout;
        this.vehicle = vehicle;
        this.vacant = vacant;
        this.user = user;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsCheckin() {
        return isCheckin;
    }

    public void setIsCheckin(int isCheckin) {
        this.isCheckin = isCheckin;
    }

    public int getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(int isCheckout) {
        this.isCheckout = isCheckout;
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getCheckinFormat() {
        return checkinFormat;
    }

    public void setCheckinFormat(String checkinFormat) {
        this.checkinFormat = checkinFormat;
    }

    public String getCheckoutFormat() {
        return checkoutFormat;
    }

    public void setCheckoutFormat(String checkoutFormat) {
        this.checkoutFormat = checkoutFormat;
    }
    public void formatCheckin(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        this.checkinFormat = sdf.format(checkin);
    }
    public void formatCheckout(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        this.checkinFormat = sdf.format(checkout);
    }
}
