package controllers;

import models.User;
import views.UserView;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserFileController {

    private static final String FILEPATH = "src/main/resources/usersDataBase.txt";

    public static boolean writeUsersToDataBaseFile(Map<String, User> usersMap) {
        File file = new File(FILEPATH);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
            for (Map.Entry<String, User> map : usersMap.entrySet()) {
                writer.println(map.getKey() + ";;" + map.getValue());
            }
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Map<String, User> readUsersFromDataBaseFile() {
        File file = new File(FILEPATH);

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
                User user = new User(splitLine(currentLine)[1], splitLine(currentLine)[2]);
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