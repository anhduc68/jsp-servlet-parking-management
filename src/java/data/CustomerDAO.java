package data;

import business.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {

    public CustomerDAO() {

    }

    public static int insert(Customer customer) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into tblCustomer(fullname, address , tel ) values(?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getFullname());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getTel());
            return ps.executeUpdate();
        } catch (SQLException e) {
           // e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }
    
    public static Customer getCustomerByID( int id ){
        Customer c  = new Customer();
        c.setId(id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblCustomer where id = ?";
         try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c.setAddress(rs.getString("address"));
                c.setFullname(rs.getString("fullname"));
                c.setTel(rs.getString("tel"));
            }   
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        
        return c;
    }
    
    public static ArrayList<Customer> getList(){
        ArrayList<Customer> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblCustomer";
         try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setAddress(rs.getString("address"));
                c.setFullname(rs.getString("fullname"));
                c.setTel(rs.getString("tel"));
                list.add(c);
            }   
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }
    
    public static ArrayList<Customer> findCustomerByName( String name ){
         ArrayList<Customer> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblCustomer where fullname LIKE ?";
         try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setAddress(rs.getString("address"));
                c.setFullname(rs.getString("fullname"));
                c.setTel(rs.getString("tel"));
                list.add(c);
            }   
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
        
        
    }
}
