package data;

import business.Customer;
import business.User;
import business.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAO {

    public VehicleDAO() {

    }

    public static int insert(Vehicle v) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into tblVehicle(type, licence_plate , customer_id) values(?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, v.getType());
            ps.setString(2, v.getLicencePlate());
            ps.setInt(3, v.getCustomer().getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }

    public static Vehicle getVehicleID(int id) {
        Vehicle v = new Vehicle();
        v.setId(id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVehicle where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setLicencePlate(rs.getString("licence_plate"));
                v.setType(rs.getString("type"));
                v.setCustomer(CustomerDAO.getCustomerByID(rs.getInt("customer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return v;
    }

    public static int isExist(String licencePlate) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVehicle where licence_plate = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, licencePlate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }

    public static ArrayList<Vehicle> getList() {
        ArrayList<Vehicle> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVehicle";
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setLicencePlate(rs.getString("licence_plate"));
                v.setType(rs.getString("type"));
                v.setCustomer(CustomerDAO.getCustomerByID(rs.getInt("customer_id")));
                list.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }

    public static ArrayList<Vehicle> getListVehicleToCheckin() {
        ArrayList<Vehicle> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVehicle \n"
                + "where id NOT IN\n"
                + "(select vehicle_id\n"
                + "from tblbooking\n"
                + "where (isCheckin = 1 and isCheckout = 0)\n"
                + "or (isCheckin = 0 and isCheckout = 0))";
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setLicencePlate(rs.getString("licence_plate"));
                v.setType(rs.getString("type"));
                v.setCustomer(CustomerDAO.getCustomerByID(rs.getInt("customer_id")));
                list.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }

    public static ArrayList<Vehicle> findVehicleByLicencePlate(String licencePlate) {
        ArrayList<Vehicle> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVehicle where licence_plate like ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + licencePlate + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setLicencePlate(rs.getString("licence_plate"));
                v.setType(rs.getString("type"));
                v.setCustomer(CustomerDAO.getCustomerByID(rs.getInt("customer_id")));
                list.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }

}
