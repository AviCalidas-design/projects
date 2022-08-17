package revature.services;

import revature.dao.UserDao;
import revature.models.User;

public class UserService {
    private UserDao udao;
    public UserService()
    {
        this.udao = new UserDao();
    }
    public UserService(UserDao theDao)
    {
        this.udao = theDao;
    }
    public boolean authenticate(User credentials) {

        User user = udao.findUser(credentials.getUsername());
        if(user == null){
            return false;
        }

        if(user.getPassword().equals(credentials.getPassword())){
            return true;
        }

        return false;
    }
    public User getUser(String username){
        return udao.findUser(username);
    }
    public User createUser(User userToCreate){

        User userFromDb = udao.findUser(userToCreate.getUsername());

        if(userFromDb == null){
            udao.createUser(userToCreate);
            return userToCreate;
        }

        return null;
    }
}
