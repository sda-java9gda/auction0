package controllers;

import models.User;
import views.UserView;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserFileController {

    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static boolean writeUsersToDataBaseFile(User user, String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println(user.getLogin() + ";;" + user.getPassword());
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, User> readUsersFromDataBaseFile(String filePath) {
        File file = new File(filePath);

        Map<String, User> usersMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;
            while (true) {
                currentLine = reader.readLine();
                if (currentLine == null) {
                    reader.close();
                    break;
                }
                User user = new User(splitLine(currentLine)[0], splitLine(currentLine)[1]);
                usersMap.put(user.getLogin(), user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersMap;
    }

//    public static boolean removeUserFromFile(User user) {
//
//        try {
//            File file = new File(FILEPATH);
//
//            File tempFile = new File(file.getAbsolutePath() + ".tmp");
//
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            PrintWriter printer = new PrintWriter(new FileWriter(tempFile));
//
//            String currentLine = null;
//            while ((currentLine = reader.readLine()) != null) {
//
//                if (!splitLine(currentLine)[0].equalsIgnoreCase(user.getLogin())) {
//
//                    printer.println(currentLine);
//                    printer.flush();
//                }
//            }
//            printer.close();
//            reader.close();
//
//            if (!file.delete()) {
//                return false;
//            }
//
//            if (!tempFile.renameTo(file)) {
//                return false;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public static boolean isUserRegisteredInDataBaseFile(User user) {
//        File file = new File(FILEPATH);
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String currentLine;
//            boolean isWorking = true;
//            while (isWorking) {
//                currentLine = reader.readLine();
//                if (currentLine == null) {
//                    reader.close();
//                    UserView.userDoNotExist();
//                    return false;
//                }
//                if (splitLine(currentLine)[0].equalsIgnoreCase(user.getLogin())) {
//
//                    return true;
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    public static String[] splitLine(String line) {

        return line.split(";;");
    }

}
