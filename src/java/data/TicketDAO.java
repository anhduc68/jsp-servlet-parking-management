package data;

import business.Ticket;
import business.User;
import business.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDAO {

    public TicketDAO() {

    }

    public static int addTurnTicket(Ticket ticket) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into tblTicket(price, user_id, vehicle_id  ) values(?,?,?)";
        try {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setFloat(1, ticket.getPrice());
            ps.setInt(2, ticket.getUser().getId());
            ps.setInt(3, ticket.getVehicle().getId());
            ps.executeUpdate();
            ResultSet generatedKey = ps.getGeneratedKeys();
            if (generatedKey.next()) {
                ticket.setId(generatedKey.getInt(1));
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }

    public static int hasMonthTicket(Vehicle v) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        Date d = new Date();
        String time2 = sdf2.format(d);
        time2 = time2 + "-01";
        String query = "select * from tblTicket \n"
                + "where vehicle_id = ?\n"
                + "and expiryDate >=? and expiryDate < DATE_ADD( ?, INTERVAL 1 MONTH)"
                + "and is_month_ticket='1'";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, v.getId());
            ps.setString(2, time2);
            ps.setString(3, time2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }

    public static int hasVipTicket(Vehicle v) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        Date d = new Date();
        String time2 = sdf2.format(d);
        time2 = time2 + "-01";
        String query = "select * from tblTicket \n"
                + "where vehicle_id = ?\n"
                + "and expiryDate >=? and expiryDate < DATE_ADD( ?, INTERVAL 1 MONTH)"
                + "and is_vip_ticket='1'";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, v.getId());
            ps.setString(2, time2);
            ps.setString(3, time2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
        return 0;
    }

    public static Ticket getTicketByVehicle(Vehicle v) throws ParseException {
        Ticket t = new Ticket();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        Date d = new Date();
        String time2 = sdf2.format(d);
        time2 = time2 + "-01";
        String query = "select * from tblTicket \n"
                + "where vehicle_id = ?\n"
                + "and expiryDate >=? and expiryDate < DATE_ADD( ?, INTERVAL 1 MONTH)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, v.getId());
            ps.setString(2, time2);
            ps.setString(3, time2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setPrice(rs.getFloat("price"));
                t.setExpiryDate(sdf1.parse(rs.getString("expiryDate")));
                t.setVacant(VacantDAO.getVacantByID(rs.getInt("vacant_id")));
                //is_month_ticket
                t.setIsVipTicket(rs.getInt("is_vip_ticket"));
                t.setIsMonthTicket(rs.getInt("is_month_ticket"));
                t.setUser(UserDAO.getUserByID(rs.getInt("user_id")));
                t.setVehicle(VehicleDAO.getVehicleID(rs.getInt("vehicle_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return t;
    }

    public static Ticket getTicketByID(int id) throws ParseException {
        Ticket t = new Ticket();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "select * from tblTicket \n"
                + "where id = ?\n";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                t.setId(rs.getInt("id"));
                t.setPrice(rs.getFloat("price"));
                t.setVacant(VacantDAO.getVacantByID(rs.getInt("vacant_id")));
                //is_month_ticket
                t.setUser(UserDAO.getUserByID(rs.getInt("user_id")));
                t.setVehicle(VehicleDAO.getVehicleID(rs.getInt("vehicle_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return t;
    }
}
