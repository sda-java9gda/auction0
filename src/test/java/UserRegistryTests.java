import controllers.UserControler;
import exceptions.DuplicateFoundException;
import models.User;
import models.UserRegistry;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserRegistryTests {

    String login, password;

    @Test
    public void addUserAccountTest(){
        new UserControler().addUser("tomek","1234");
    }

}
