package data;

import business.Customer;
import business.User;
import business.Vacant;
import business.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VacantDAO {

    public VacantDAO() {

    }

    public static int insert(Vacant v) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into tblVacant(name, area , type, price) values(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, v.getName());
            ps.setFloat(2, v.getArea());
            ps.setString(3, v.getType());
            ps.setFloat(4, v.getPrice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }

    public static Vacant getVacantByID(int id) {
        Vacant v = new Vacant();
        v.setId(id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVacant where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setName(rs.getString("name"));
                v.setPrice(rs.getFloat("price"));
                v.setType(rs.getString("type"));
                v.setArea(rs.getFloat("area"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return v;
    }

    public static ArrayList<Vacant> getListVacantToCheckin(String type) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
        Date d = new Date();
        String time1 = sdf1.format(d);
        String time2 = sdf2.format(d);
        time2 = time2 + "-01";
        ArrayList<Vacant> list = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblVacant \n"
                + "where type = ? \n"
                + "and id NOT IN\n"
                + "(select vacant_id from tblbooking\n"
                + "	where ? > checkin and ? < DATE_ADD( checkin , INTERVAL  2 HOUR)\n"
                + "    and isCheckin = 0\n"
                + ")\n"
                + "and id NOT IN\n"
                + "(select vacant_id from tblbooking\n"
                + "	where isCheckin = 1 and isCheckout = 0\n"
                + ")\n"
                + "and id NOT IN\n"
                + "( select vacant_id from tblticket\n"
                + "where is_vip_ticket = 1 and\n"
                + "expiryDate >=? and expiryDate < DATE_ADD( ?, INTERVAL 1 MONTH)\n"
                + ")";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, type);
            ps.setString(2, time1);
            ps.setString(3, time1);
            ps.setString(4, time2);
            ps.setString(5, time2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vacant v = new Vacant();
                v.setId(rs.getInt("id"));
                v.setName(rs.getString("name"));
                v.setPrice(rs.getFloat("price"));
                v.setType(rs.getString("type"));
                v.setArea(rs.getFloat("area"));
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
