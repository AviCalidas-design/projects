//package revature;
import java.util.List;
import java.util.Scanner;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import java.util.logging.LogManager;
import java.util.logging.Logger;
/*
import views.*;
import dao.*;
import models.*;
import util.*;
*/
//import views.MainMenu;
public class App {
    static Logger logger = Logger.getLogger("App.class");
    public static void main( String[] args )
    {
        AccountDao.initialize();
        UserDao.initialize();
        MainMenu mainMenu = new MainMenu();
        mainMenu.view();
        try
        {
            AccountDao.close();
            UserDao.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
