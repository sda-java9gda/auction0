package view;

import models.User;

import java.util.List;

public class UserView {
    public static String printUsers(List<User> accountants) {
        StringBuilder sb = new StringBuilder();
        for (User user : accountants) {
            sb.append(user.getLogin()).append("\n");
        }
        return sb.toString();
    }

    public static String printLoginSuccess(String login) {
        return "Dzien dobry " + login;
    }

    public static String printLoginNotFound(String login) {
        return "Nie znaleziono uzytkownika " + login;
    }

    public static String printAddAccountSuccess(String login) {
        return "Dodano uzytkownika " + login;
    }

    public static String printDuplicateFound(String login) {
        return "Uzytkownik " + login + " juz istnieje.";
    }
    public static String printRemoveAccountSuccess (String login){
        return "Usunieto uzytkownika " + login;
    }

}
