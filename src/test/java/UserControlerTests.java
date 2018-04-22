import controllers.UserControler;
import models.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserControlerTests {

    @Test
    public void createUserTest() {

        User expected = new User("tomek", "1234");
        UserControler userControler = new UserControler();
        User actual = userControler.createUser("tomek", "1234");
        assertEquals(expected, actual);
        //test na tworzenie obiektu, na dodanie do listy, na to czy istnieje do listy
    }

//    @Test
//    public void createUserAddingToListTest() {
//        User user = new User("tomek", "1234");
//        UserControler userControler = new UserControler();
//        userControler.createUser("tomek", "1234");
//        assert
//    }

}
