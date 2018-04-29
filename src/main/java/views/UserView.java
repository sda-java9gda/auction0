package views;

import models.User;

public class UserView {

    public static void welcomeUser(String loggin) {
        System.out.println("you have successfully logged in as " + loggin);
    }

    public static void logoutMessage() {
        System.out.println("successful logout from the account");
    }

    public static void accountRegistered() {
        System.out.println("Your account has been successfully registered. Now you can log in.");
    }

    public static void accountRemoved() {
        System.out.println("Your account has been successfully removed");
    }

    public static void userDoNotExist() {
        System.out.println("Such user does not exist");
    }

    public static void loginIsTaken(String login) {
        System.out.println("Login " + login + " is taken");
    }

    public static void wrongPasswordOrLogin() {
        System.out.println("Wrong login or password");
    }

    public static void enterLogin() {
        System.out.println("Enter your login: ");
    }

    public static void enterPassword() {
        System.out.println("Enter your password: ");
    }

    public static void exitProgramMessage(){
        System.out.println("Goodbye");
    }
}
