/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import business.Booking;
import business.Customer;
import business.User;
import business.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dieu Dai Hiep
 */
public class BookingDAO {

    public static int insert(Booking booking) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "insert into tblBooking(checkin, price , isCheckin, isCheckout, user_id, vacant_id , "
                + "vehicle_id, ticket_id ) values(?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, sdf1.format(booking.getCheckin()));
            ps.setFloat(2, booking.getPrice());
            ps.setInt(3, booking.getIsCheckin());
            ps.setInt(4, booking.getIsCheckout());
            ps.setInt(5, booking.getUser().getId());
            ps.setInt(6, booking.getVacant().getId());
            ps.setInt(7, booking.getVehicle().getId());
            ps.setInt(8, booking.getTicket().getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }

    public static Booking getBookingByID(int id) throws ParseException {
        Booking b = new Booking();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "select * from tblBooking where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b.setId(rs.getInt("id"));
                b.setCheckin(sdf1.parse(rs.getString("checkin")));
                b.setPrice(rs.getFloat("price"));
                b.setIsCheckin(rs.getInt("isCheckin"));
                b.setUser(UserDAO.getUserByID(rs.getInt("user_id")));
                b.setVacant(VacantDAO.getVacantByID(rs.getInt("vacant_id")));
                b.setVehicle(VehicleDAO.getVehicleID(rs.getInt("vehicle_id")));
                b.setTicket(TicketDAO.getTicketByID(rs.getInt("ticket_id")));
                b.formatCheckin();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return b;

    }

    public static int checkinBooking(Booking booking) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String time1 = sdf1.format(d);
        String query = "update tblBooking SET isCheckin = ?, checkin = ?  where id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setString(2, time1);
            ps.setInt(3, booking.getId());
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }

    }

    public static int checkoutBooking(Booking booking) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String time1 = sdf1.format(d);
        String query = "update tblBooking SET isCheckout = ?, checkout = ?  where id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, 1);
            ps.setString(2, time1);
            ps.setInt(3, booking.getId());
            ps.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }

    }

    public static ArrayList<Booking> getListBookingToCheckin() throws ParseException {
        ArrayList<Booking> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = new Date();
        String time1 = sdf1.format(d);
        String query = "select * from tblBooking\n"
                + "where isCheckin = 0\n"
                + "and ? > checkin and ? < DATE_ADD( checkin , INTERVAL  2 HOUR)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, time1);
            ps.setString(2, time1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setCheckin(sdf1.parse(rs.getString("checkin")));
                b.setPrice(rs.getFloat("price"));
                b.setIsCheckin(rs.getInt("isCheckin"));
                b.setUser(UserDAO.getUserByID(rs.getInt("user_id")));
                b.setVacant(VacantDAO.getVacantByID(rs.getInt("vacant_id")));
                b.setVehicle(VehicleDAO.getVehicleID(rs.getInt("vehicle_id")));
                b.formatCheckin();
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }

    public static ArrayList<Booking> getListBookingToCheckout() throws ParseException {
        ArrayList<Booking> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String query = "select * from tblBooking\n"
                + "where isCheckin = 1\n"
                + "and isCheckout=0";
        try {
            ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getInt("id"));
                b.setCheckin(sdf1.parse(rs.getString("checkin")));
                b.setPrice(rs.getFloat("price"));
                b.setIsCheckin(rs.getInt("isCheckin"));
                b.setUser(UserDAO.getUserByID(rs.getInt("user_id")));
                b.setVacant(VacantDAO.getVacantByID(rs.getInt("vacant_id")));
                b.setVehicle(VehicleDAO.getVehicleID(rs.getInt("vehicle_id")));
                b.formatCheckin();
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return list;
    }

    public static int countCheckinInADay(Vehicle v) {
        int count = 0;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String time = sdf1.format(d);
        String query = "select count(vehicle_id) as count\n"
                + "from tblbooking \n"
                + "where vehicle_id = ? \n"
                + "and  \n"
                + "checkin >= ? and checkin <  DATE_ADD( ?, INTERVAL 1 DAY )\n"
                + "and isCheckin = 1 and isCheckout = 1\n"
                + "group by vehicle_id";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, v.getId());
            ps.setString(2, time);
             ps.setString(3, time);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return count;
    }

}
