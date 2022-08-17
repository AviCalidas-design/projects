package revature;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import revature.views.*;
import revature.dao.*;
import revature.models.*;
import revature.util.*;
public class App {
    static Logger logger = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        MainMenu mainMenu = new MainMenu();
        mainMenu.view();
    }
}
