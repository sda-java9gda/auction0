package controllers;

import exceptions.IllegalCharException;
import exceptions.LoginTakenException;
import exceptions.NoSuchUserException;
import models.User;

import java.util.*;

public class UserController {

    public Map<String, User> getMapOfUsers(String filePath) {
        Map<String, User> usersMap = UserFileController.readUsersFromFile(filePath);
        if (usersMap != null) {
            return usersMap;
        }
        return usersMap = new HashMap<>();
    }

    public void addUser(String login, String password, String filePath, Map<String, User> usersMap) throws LoginTakenException {
        User user = new User(login, password);
        if (!isUserExist(login, usersMap)) {
            usersMap.put(login, user);
            UserFileController.writeUsersToFile(user, filePath);
            return;
        }
        throw new LoginTakenException();
    }

    public void removeUser(String login, String filePath, Map<String, User> usersMap) throws NoSuchUserException {
        if (isUserExist(login, usersMap)) {
            usersMap.remove(login);
            UserFileController.removeUserFromFile(login, filePath);
            return;
        }
        throw new NoSuchUserException();
    }

    public boolean isUserExist(String login, Map<String, User> usersMap) {
        return usersMap.containsKey(login);
    }

    public void isLoginAndPasswordCorrect(String login, String password, Map<String, User> usersMap) throws NoSuchUserException {

        for (Map.Entry<String, User> map : usersMap.entrySet()) {
            if ((map.getKey().trim().equals(login.trim())) && (map.getValue().toString().split(";")[1].trim().equals(password.trim())))
                return;
        }
        throw new NoSuchUserException();
    }

    public void isPasswordContainsIllegalChars(String password) throws IllegalCharException {
        String[] splits = password.split("");
        for (String split : splits) {
            if (split.contains(";")) {
                throw new IllegalCharException();
            }
        }
    }
}