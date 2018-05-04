package views;

import models.User;

public class UserView {

    public static void enterLogin() {
        System.out.println("Enter your login: ");
    }

    public static void enterPassword() {
        System.out.println("Enter your password: ");
    }

    public static void exitProgram() {
        System.out.println("Goodbye!");
    }

    public static void loggedIn(String loggin) {
        System.out.println("You have successfully logged in as " + loggin + ".");
    }

    public static void loggedOut() {
        System.out.println("You have successfully logged out.");
    }

    public static void accountRegistered() {
        System.out.println("Your account has been successfully registered. Now you can log in.");
    }

    public static void accountRemoved() {
        System.out.println("Your account has been successfully removed.");
    }

    public static void loginIsTaken(String login) {
        System.out.println("Login " + login + " is taken.");
    }

    public static void wrongPasswordOrLogin() {
        System.out.println("Wrong login or password.");
    }


    public static void illegalChar() {
        System.out.println("Password can not contain this symbol \";\".");
    }
}
