package revature.views;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import revature.models.User;
import revature.services.UserService;
import revature.util.InputUtil;
public class MainMenu {
    static Logger logger = LogManager.getLogger(MainMenu.class);

    UserService userService = new UserService();
    InputUtil inputUtil = new InputUtil();
    public void view(){
        Scanner scanner = new Scanner(System.in);
        Boolean done = false;

        while(done){
            System.out.println( "Bank Account App!\n1) login\n2) register\n0) EXIT" );
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    String usernameInput = inputUtil.retrieveString("Username: ");
                    String passwordInput = inputUtil.retrieveString("Password: ");

                    Boolean areCredentialsValid = userService.authenticate(new User(usernameInput, passwordInput));

                    if(areCredentialsValid){
                        logger.info("User " + usernameInput + " successfully logged into system");
                        User userFromDb = this.userService.getUser(usernameInput);
                        new UserActions().view_general(userFromDb);
                    }else{
                        System.out.println("Invalid username or password");
                    }

                    break;
                case "2":
                    String usernameInputReg = inputUtil.retrieveString("Username: ");
                    String passwordInputReg = inputUtil.retrieveString("Password: ");
                    String firstNameInputReg = inputUtil.retrieveString("First Name: ");
                    String lastNameInputReg = inputUtil.retrieveString("Last Name: ");
                    String typeInputReg = inputUtil.retrieveString("Employee, customer, or both: ");

                    User userToCreate = new User(usernameInputReg, passwordInputReg, firstNameInputReg, lastNameInputReg, typeInputReg);

                    User userFromDb = this.userService.createUser(userToCreate);
                    if(userFromDb == null){
                        System.out.println("Username already exists... aborting");
                    }else{
                        System.out.println("User Created");
                    }
                    break;
                case "0":
                    done = true;
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }       

    }
}
}
