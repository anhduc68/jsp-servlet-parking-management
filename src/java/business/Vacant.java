
package business;


public class Vacant {
    private int id;
    private String name;
    private float area;
    private String type;
    private float price;

    public Vacant() {
    }

    public Vacant(int id, String name, float area, String type, float price) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public String toString(){
        return id+" "+name+" "+type+" "+area+" "+" "+price;
    }
}
