
package business;
public class Vehicle {
    private int id;
    private String type;
    private String licencePlate;
    private Customer customer;

    public Vehicle() {
    }

    public Vehicle(int id, String type, String licencePlate, Customer customer) {
        this.id = id;
        this.type = type;
        this.licencePlate = licencePlate;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
    
   
    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
     public String toString(){
        return id+" "+type+" "+licencePlate+" "+customer.toString();
    }

    
}
