import models.User;
import org.junit.Assert;
import org.junit.Test;
import view.UserView;

import java.util.ArrayList;
import java.util.Arrays;

public class UserViewTests {

    @Test
    public void printUserTest(){
        User user1 = new User("tom","123");
        User user2 = new User("luk","456");
        ArrayList<User>users=new ArrayList<>(Arrays.asList(user1,user2));

        String expected = "tom\nluk";
        String result =  UserView.printUsers(users);
    }

    @Test
    public void printLoginSuccessTest(){
        String expected = "Dzien dobry tom";
        String actual= UserView.printLoginSuccess("tom");
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void printLoginNotFoundTest(){
        String expected = "Nie znaleziono uzytkownika tom";
        String actual = UserView.printLoginNotFound("tom");

        Assert.assertEquals(expected,actual);
    }
}
