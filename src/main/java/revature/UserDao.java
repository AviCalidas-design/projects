//package revature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import models.User;
import models.Account;
import util.InputUtil;
import util.ConnectionUtil;
*/
public class UserDao {
    static Logger logger = Logger.getLogger("UserDao.class");
    private static List<User> users = new ArrayList<User>();
    private static FileWriter dataWriter;
    public List<User> getUsers()
    {
        /*
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
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            //logger.error("Sql Exception Occured", e);
        }
        User.updateTotal(total);
        */
        return users;
    }
    public User findUser(String username) {
        /*
        User user = null;
    
        System.out.println("We got here!");
        try(Connection conn = ConnectionUtil.getConnection()) 
        {
            //System.out.println("We got here!");
            String sql = "select * from users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            //System.out.println("We got here!");
            while(rs.next()){
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            
        } catch (SQLException e) {
            //logger.error("Sql Exception Occured", e);
        }
        */
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUsername().equals(username))
            {
                return users.get(i);
            }
        }
        return null;
    }
    public static void initialize()
    {
        try
        {
            Scanner dataCollect = new Scanner(new File("UserData.txt"));
            while(dataCollect.hasNextLine())
            {
                String data = dataCollect.nextLine();
                String[] values = data.split(",");
                User addition = new User(values[0],values[1],values[2], values[3],values[4]);
                addition.giveId();
                users.add(addition);
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void close() throws Exception
    {
        dataWriter = new FileWriter("UserData.txt");
        for(User user : users)
        {
            dataWriter.write(user.getUsername() + "," + user.getPassword() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getType() + "," + user.getId()+"\n");
        }
        dataWriter.close();
    }
    public void createUser(User user) {
        users.add(user);
            /*
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
            //logger.error("Sql Exception Occured", e);
        }
        */
    }
}
