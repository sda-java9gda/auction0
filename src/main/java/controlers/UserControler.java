package controlers;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserControler {

    private List usersList = new ArrayList();

    public void removeUser(User user) {
        if (usersList.contains(user)) {
            usersList.remove(user);
        }
    }

}
