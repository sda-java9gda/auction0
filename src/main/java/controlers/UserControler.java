package controlers;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserControler {

    private List<User> usersList = new ArrayList<User>();

    public void createUser(String login, String passowrd) {
        User user = new User();
        user.setName(login);
        user.setPassword(passowrd);
        usersList.add(user);
    }

    public void loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Login: ");
        String login = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();

        for (User user : usersList) {
            if (user.getName().equalsIgnoreCase(login) && user.getPassword().equals(password)) {
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
