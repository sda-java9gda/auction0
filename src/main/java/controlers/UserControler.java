package controlers;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserControler {

    private List usersList = new ArrayList();

    public void addUser(User user){
     usersList.add(user);
    }
}
