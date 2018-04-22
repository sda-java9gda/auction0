package controllers;

import exceptions.DuplicateFoundException;
import exceptions.UserNotFoundException;
import models.UserRegistry;
import view.UserView;

public class UserControler {

    public static boolean addUser(String login, String password) {
        try {
            UserRegistry.getInstanceOfUser().addUserAccount(login, password);
        } catch (DuplicateFoundException e) {
            System.err.println(UserView.printDuplicateFound(login));
        }
        return false;
    }

    public static boolean loginUser(String login, String password) {
        try {
            UserRegistry.getInstanceOfUser().findUser(login, password);
            System.out.println(UserView.printLoginSuccess(login));
            return true;
        } catch (UserNotFoundException e) {
            System.err.println(UserView.printLoginNotFound(login));
        }
        return false;
    }
}
