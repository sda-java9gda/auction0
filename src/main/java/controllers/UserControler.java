package controllers;

import exceptions.DuplicateFoundException;
import exceptions.UserNotFoundException;
import models.User;
import view.UserView;

import java.util.*;

public class UserControler {


    private List<User> usersList = new ArrayList<User>(); // login User HashMap


    public static boolean addUser(String login, String password) {
        try {
            UserRegistry.getInstanceOfUser().addUserAccount(login, password);
        } catch (DuplicateFoundException e) {
            System.err.println(UserView.printDuplicateFound(login));
        }
        return false;
    }

    public static boolean loginUser(String login, String password){
        try {
            UserRegistry.getInstanceOfUser().findUser(login,password);
            System.out.println(UserView.printLoginSuccess(login));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean isLoginExist(String login) {
        for (User user : usersList) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public User createUser(String login, String password) {
        User user = new User(login, password);
        return user;
    }

    public void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Login: ");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();

        for (User user : usersList) {
            if (user.getLogin().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
                System.out.println("zalogowałeś sie");
            } else System.out.println("Błędne hasło lub login");
        }
    }

    public void removeUser(User user) {
        if (usersList.contains(user)) {
            usersList.remove(user);
        }
    }

}
