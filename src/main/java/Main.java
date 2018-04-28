import controllers.UserController;
import controllers.UserFileController;
import models.States;
import models.User;
import views.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        States state = States.INIT;
        UserController userController = new UserController();
        Map<String, User> usersMap = new HashMap<>();
        final String USERS_FILEPATH = "src/main/resources/usersDataBase.txt";

        do {
            switch (state) {
                case INIT:
                    System.out.println("Hello! What you want to do?");
                    System.out.println("Press \"1\"  to login the user");
                    System.out.println("Press \"2\"  to register the user");
                    System.out.println("Press \"0\"  to exit");
                    if (!UserFileController.isFileExist(USERS_FILEPATH)){
                    UserFileController.writeUsersToDataBaseFile(usersMap,USERS_FILEPATH);
                    }
                    usersMap = userController.getMapOfUsers(USERS_FILEPATH);
                    String decision = sc.nextLine();
                    switch (decision) {

                        case ("1"):
                            state = States.LOGIN;
                            break;
                        case ("2"):
                            state = States.REGISTRATION;
                            break;
                        case ("0"):
                            state = States.EXIT;
                            break;
                        default:
                            System.out.println("Invalid character!");
                            state = States.INIT;
                            break;
                    }
                    break;
                case REGISTRATION: {
                    boolean isRegistered;
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();

                    isRegistered = userController.addUser(login, password, usersMap);
                    if (isRegistered) {
                        state = States.LOGIN;
                        UserView.accountRegistered();
                    } else {
                        state = States.INIT;
                        UserView.loginIsTaken(login);
                    }
                    break;
                }
                case LOGIN: {
                    boolean isLogged;
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();

                    isLogged = userController.logInUser(login, password, usersMap);
                    if (isLogged) {
                        UserView.welcomeUser(login);
                        state = States.LOGGED;
                    } else {
                        UserView.wrongPasswordOrLogin();
                        state = States.LOGIN;
                    }
                    break;
                }
                case LOGGED:
                    System.out.println("Hello! What you want to do?");
                    System.out.println("Press \"1\"  to view the auctions");
                    System.out.println("Press \"2\"  to add auction");
                    System.out.println("Press \"3\"  to bid");
                    System.out.println("Press \"0\"  to log out");
                    decision = sc.nextLine();
                    switch (decision) {
                        case "1":
                            //wyswietl liste aukcji
                        case "2":
                            //dodaj aukcje
                        case "3":
                            //licytuj
                        case "0":
                            UserView.logoutMessage();
                            state = States.INIT;
                            break;
                    }
                case EXIT:
                    userController.saveUsersMapToFile(usersMap, USERS_FILEPATH);
                    UserView.exitProgramMessage();
                    break;
            }
        } while (state != States.EXIT);
    }
}
