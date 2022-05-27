package data;

import business.Customer;
import business.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public UserDAO() {

    }

    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "insert into tblUser(fullname, username , password, position ) values(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFullname());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPosition());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            pool.freeConnection(connection);
        }
    }

    public static int checkLogin(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblUser where username=? and password =?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setPosition(rs.getString("position"));
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

    public static User getUserByID(int id) {
        User u = new User();
        u.setId(id);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "select * from tblUser where id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                u.setPosition(rs.getString("position"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.freeConnection(connection);
        }
        return u;
    }
}
