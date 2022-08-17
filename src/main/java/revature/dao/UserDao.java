package revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import revature.models.*;
import revature.util.*;
public class UserDao {
    static Logger logger = LogManager.getLogger(UserDao.class);
    public List<User> getUsers()
    {
        List<User> users = new ArrayList<>();
        int total = 0;
        try {
            Connection c = ConnectionUtil.getConnection();

            String s = "select * from users";
            PreparedStatement p = c.prepareStatement(s);

            ResultSet r = p.executeQuery();

            while(r.next()){
                total += 1;
                users.add(new User(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5)));
            }

            c.close();
            
        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
        User.updateTotal(total);
        return users;
    }
    public User findUser(String username) {
        
        User user = null;
        
        try(Connection conn = ConnectionUtil.getConnection()) 
        {
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            
        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }

        return user;
    }
    public void createUser(User user) {
        try {
            Connection conn = ConnectionUtil.getConnection();

            String sql = "insert into users (firstname, lastname, username, pswd, ty, user_id) values (?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getType());
            ps.setInt(6, user.getId());

            ps.executeUpdate();

            conn.close();
            
        } catch (SQLException e) {
            logger.error("Sql Exception Occured", e);
        }
    }
}
