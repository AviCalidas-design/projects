//package revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/*
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
*/
public class ConnectionUtil {
    static Logger logger = Logger.getLogger("ConnectionUtil.class");
    private static final String url = "jdbc:postgresql://first-database.cyenimr1bknd.us-west-1.rds.amazonaws.com/first_database";
    private static final String username = "avicalidas";
    private static final String password = "supermario111";
    public static Connection getConnection(){

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
            logger.info(e.getMessage());
        }

        return conn;
    }
}
