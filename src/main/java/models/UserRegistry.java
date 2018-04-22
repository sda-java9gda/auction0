package models;

import exceptions.DuplicateFoundException;
import exceptions.UserNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;

public class UserRegistry implements Serializable {
    public static UserRegistry instance = null;
//    final private String filename = "data/users.dat";

    public static UserRegistry getInstanceOfUser() {
        if (instance == null) {
            instance = new UserRegistry();
        }
        return instance;
    }
    private ArrayList<User> usersList;

    public UserRegistry(){
        usersList = models.FileHandler.loadUser("users");
    }

    public void findUser(String login, String password) throws UserNotFoundException {
        for (User user : usersList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return;
        }
    throw new UserNotFoundException();
    }

    public void addUserAccount(String login, String password) throws DuplicateFoundException{
        for (User user : usersList) {
            if (user.getLogin().equals(login)){
                throw new DuplicateFoundException();
            }
            usersList.add(new User(login,password));
        }

    }
    public void saveData(){
        models.FileHandler.save(usersList,"usersList");
    }

    public ArrayList<User> getUsersList() {
        return usersList;
    }
}
