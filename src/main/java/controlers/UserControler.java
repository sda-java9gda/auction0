package controlers;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserControler {

    private List<User> usersList = new ArrayList<User>();


    public boolean isLoginExist(String login) {
        for (User user : usersList) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }
        return true;
    }

    public User  createUser(String login, String password) {
        User user = new User(login,password);
        usersList.add(user);
//<<<<<<< HEAD
        return user;
//=======
//
//>>>>>>> 2f92c40c137a77cdc54e7522ea8b7164b4fff244
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

//    public void addUser(User user) {
//        usersList.add(user);
//    }

    public void removeUser(User user) {
        if (usersList.contains(user)) {
            usersList.remove(user);
        }
    }

}
