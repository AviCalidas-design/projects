

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
public class UserServiceTest {
    UserDao userDao = Mockito.mock(UserDao.class);
    UserService userService = new UserService(userDao);
    @Test
    public void testAuthenticate() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(objToPass);

        //act
        Boolean actualResult = userService.authenticate(objToPass);

        //assert
        assertTrue(actualResult);
    }
    @Test
    public void testAuthenticateFail() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(null);

        //act
        Boolean actualResult = userService.authenticate(objToPass);

        //assert
        assertFalse(actualResult);
    }

    @Test
    public void testGetUser() {
        //arrange
        User objToPass = new User("avicalidas", "hollywood");
        Mockito.when(userDao.findUser(objToPass.getUsername())).thenReturn(objToPass);

        //act
        User actualResult = userService.getUser("avicalidas");

        //assert
        assertTrue(actualResult != null);
    }
}
