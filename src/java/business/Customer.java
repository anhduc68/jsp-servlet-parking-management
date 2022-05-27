/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Dieu Dai Hiep
 */
public class Customer {
    private int id;
    private String fullname;
    private String address;
    private String tel;

    public Customer() {
    }

    public Customer(int id, String fullname, String address, String tel) {
        this.id = id;
        this.fullname = fullname;
        this.address = address;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Override
    public String toString(){
        return id+" "+ fullname+" "+ address+" "+tel;
    }

}
