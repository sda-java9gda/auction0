package controlers;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserControler {

    private List<User> usersList = new ArrayList<User>();

    public void addUser(User user) {
        usersList.add(user);
    }

    public void removeUser(User user) {
        if (usersList.contains(user)) {
            usersList.remove(user);
        }
    }
}
