import controllers.UserController;
import controllers.UserFileController;
import exceptions.IllegalCharException;
import exceptions.NoSuchUserException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import models.User;
import org.hamcrest.collection.IsMapContaining;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class UserControllerTest {

    private UserController uc;
    private Map<String, User> usersMap;
    private String filePath;

    public Object paramsForTrue() {
        return new Object[][]{
                {new User("Test1", "123"), true},
                {new User("Test2", "123"), true},
                {new User("Test3", "qwerty"), true},
                {new User("Test4", "0"), true}
        };
    }

    public Object paramsForFalse() {
        return new Object[][]{
                {new User("Test1", "123"), false},
                {new User("Test2", "123"), false},
                {new User("Test3", "qwerty"), false},
                {new User("Test4", "0"), false}
        };
    }

    @Before
    public void setUp() {
        uc = new UserController();
        usersMap = new HashMap<>();
        filePath = "src/test/resources/TestsUsers.txt";
    }

    @After
    public void cleanUp() throws Exception {
        Files.deleteIfExists(Paths.get(filePath));
    }


    //Testing getMapOfUsers
    @Test
    public void getMapOfUsersShouldReturnMapThatContainsKey() throws Exception {
        Files.write(Paths.get(filePath), "login;1234\n".getBytes(), StandardOpenOption.CREATE);

        Map<String, User> actual = uc.getMapOfUsers(filePath);

        assertTrue(actual.containsKey("login"));
    }

    @Test
    public void getMapOfUsersShouldReturnMapThatContainsUser() throws Exception {
        User user = new User("login", "1234");
        Files.write(Paths.get(filePath), user.toString().getBytes(), StandardOpenOption.CREATE);

        usersMap = uc.getMapOfUsers(filePath);
        usersMap.put(user.getLogin(), user);

        assertTrue(usersMap.containsValue(user));
    }

    @Test
    public void getMapOfUsersShouldReturnMapThatDoNotContainsUser() throws Exception {
        User user = new User("login", "1234");
        Files.write(Paths.get(filePath), "tttt;1111".getBytes(), StandardOpenOption.CREATE);

        Map<String, User> actual = uc.getMapOfUsers(filePath);

        assertFalse(actual.containsValue(user));
    }

    //Testing addUser
    @Test
    public void shouldAddUserToMap() throws Exception {
        User user = new User("login", "1234");

        uc.addUser(user.getLogin(), user.getPassword(), filePath, usersMap);
        String expected = user.toString();
        String actual = usersMap.get(user.getLogin()).toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldNotAddUserToMap() throws Exception {
        User user = new User("login", "1234");
        uc.addUser(user.getLogin(), user.getPassword(), filePath, usersMap);
        String expected = "tttt;1111";
        String actual = usersMap.get(user.getLogin()).toString();

        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    public void shouldAddUserToFile() throws Exception {
        User user = new User("login", "1234");

        uc.addUser(user.getLogin(), user.getPassword(), filePath, usersMap);
        Map<String, User> actual = uc.getMapOfUsers(filePath);

        assertThat(actual).isNotEmpty();

    }

    //Testing removeUser
    @Test
    public void shouldRemoveUser() throws Exception {
        User user = new User("tom", "1234");

        Files.write(Paths.get(filePath), user.toString().getBytes(), StandardOpenOption.CREATE);
        usersMap.put(user.getLogin(), user);

        uc.removeUser(user.getLogin(), filePath, usersMap);
        assertFalse(usersMap.containsKey(user.getLogin()));
    }

    @Test(expected = NoSuchUserException.class)
    public void shouldRemoveUserThrowException() throws Exception {
        User user = new User("tom", "1234");
        Files.write(Paths.get(filePath), user.toString().getBytes(), StandardOpenOption.CREATE);
        usersMap.put(user.getLogin(), user);
        User user1 = new User("2222", "3333");

        uc.removeUser(user1.getLogin(), filePath, usersMap);
        assertTrue(usersMap.containsKey("tom"));
    }

    //Testing isUserExist
    @Test
    @Parameters(method = "paramsForTrue")
    public void isUserExistShouldBeTrue(User user, boolean expected) throws Exception {
        uc.addUser(user.getLogin(), user.getPassword(), filePath, usersMap);
        boolean actual = uc.isUserExist(user.getLogin(), usersMap);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Parameters(method = "paramsForFalse")
    public void isUserExistShouldBeFalse(User user, boolean expected) throws Exception {
        uc.addUser(user.getLogin(), user.getPassword(), filePath, usersMap);
        boolean actual = uc.isUserExist("nonExistingLogin", usersMap);

        assertThat(actual).isEqualTo(expected);
    }

    //Testing isLoginAndPasswordCorrect
    @Test(expected = NoSuchUserException.class)
    public void shouldLoginAndPasswordThrowException() throws Exception {
        User user = new User("login", "1234");
        usersMap.put(user.getLogin(), user);
        User actual = new User("1111", "2222");

        uc.isLoginAndPasswordCorrect(actual.getLogin(), actual.getPassword(), usersMap);
    }

    @Test
    public void shouldLoginAndPasswordBeCorrect() throws Exception {
        User user = new User("login", "1234");
        usersMap.put(user.getLogin(), user);
        User actual = new User("login", "1234");

        uc.isLoginAndPasswordCorrect(actual.getLogin(), actual.getPassword(), usersMap);
    }

    //Testing isPasswordContainsIllegalChars
    @Test(expected = IllegalCharException.class)
    public void shouldPasswordContainIllegalChars() throws Exception {
        User user = new User("login", "12;34");

        uc.isPasswordContainsIllegalChars(user.getPassword());
    }

    @Test
    public void shouldNotPasswordContainIllegalChars() throws Exception {
        User user = new User("login", "1234");

        uc.isPasswordContainsIllegalChars(user.getPassword());
    }
}