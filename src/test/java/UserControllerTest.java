import controllers.UserController;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class UserControllerTest {

    private UserController uc;
    private Map<String, User> usersMap;

    public Object paramsForTrue() {
        return new Object[][]{
                {new User("Test1", "123"), true},
                {new User("Test2", "123"), true},
                {new User("DDDDDDDDDDDD", "addadasd"), true},
                {new User("0", "0"), true}
        };
    }

    public Object paramsForFalse() {
        return new Object[][]{
                {new User("Test1", "123"), false},
                {new User("Test2", "123"), false},
                {new User("DDDDDDDDDDDD", "addadasd"), false},
                {new User("0", "0"), false}
        };
    }

    @Before
    public void createObjects(){
        uc = new UserController();
        usersMap = new HashMap<>();
    }

    @Test
    @Parameters(method = "paramsForTrue")
    public void followParamsForIsUserExistShouldBeTrue(User user, boolean expected) {
        createObjects();
        //given
        //user, usersMap
        //when
        uc.addUser(user.getLogin(), user.getPassword(), usersMap);
        boolean actual = uc.isUserExist(user.getLogin(), usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "paramsForFalse")
    public void isUserExistShouldBeFalse(User user, boolean expected) {
        //given
        //user, usersMap
        //when
        uc.addUser(user.getLogin(), user.getPassword(), usersMap);
        boolean actual = uc.isUserExist("ttttt", usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "paramsForTrue")
    public void addUserShouldBeTrue(User user, boolean expected) {
        //given
        //user, usersMap
        //when
        boolean actual = uc.addUser(user.getLogin(),user.getPassword(),usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    @Parameters(method = "paramsForFalse")
    public void addUserShouldBeFalse(User user, boolean expected) {
        //given
        //user
        usersMap.put(user.getLogin(),user);
        //when
        boolean actual = uc.addUser(user.getLogin(),user.getPassword(),usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "paramsForTrue")
    public void removeUserShouldBeTrue(User user, boolean expected){
        //given
        //user
        usersMap.put(user.getLogin(),user);
        //when
        boolean actual = uc.removeUser(user,usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    @Parameters(method = "paramsForFalse")
    public void removeUserShouldBeFalse(User user, boolean expected){
        //given
        //user, userMap
        //when
        boolean actual = uc.removeUser(user,usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    @Parameters(method = "paramsForTrue")
    public void logInUserShouldBeTrue(User user, boolean expected){
        //given
        //user
        usersMap.put(user.getLogin(),user);
        //when
        boolean actual = uc.logInUser(user.getLogin(),user.getPassword(),usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    @Parameters(method = "paramsForFalse")
    public void logInUserShouldBeFalse(User user, boolean expected){
        //given
        //user, userMap
        //when
        boolean actual = uc.logInUser(user.getLogin(),user.getPassword(),usersMap);
        //then
        assertThat(actual).isEqualTo(expected);
    }
    //Napisac testy dla getMapOfUsers i saveUsersMapToFile

}
