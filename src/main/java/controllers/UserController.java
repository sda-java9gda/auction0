package controllers;

import models.User;

import java.util.*;

public class UserController {

    public Map<String, User> getMapOfUsers(String filePath) {
        Map<String, User> usersMap = UserFileController.readUsersFromDataBaseFile(filePath);
        if (usersMap != null) {
            return usersMap;
        }
        return usersMap = new HashMap<>();
    }

    public void saveUsersMapToFile(User user, String filePath) {
        UserFileController.writeUsersToDataBaseFile(user, filePath);
    }

    public boolean addUser(String login, String password, Map<String, User> usersMap) {
        User user = new User(login, password);
        if (!isUserExist(login, usersMap)) {
            usersMap.put(login, user);
            return true;
        }
        return false;
    }

    public boolean removeUser(User user,Map<String, User> usersMap) {
        if (isUserExist(user.getLogin(),usersMap)) {
            usersMap.remove(user);
            return true;
        }
        return false;
    }

    public boolean logInUser(String login, String password, Map<String,User> usersMap) {

        for (Map.Entry<String, User> map : usersMap.entrySet()) {
            if ((map.getKey().trim().equals(login.trim())) && (map.getValue().toString().split(";;")[1].trim().equals(password.trim()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isUserExist(String login,Map<String, User> usersMap) {
        return usersMap.containsKey(login);
    }
}