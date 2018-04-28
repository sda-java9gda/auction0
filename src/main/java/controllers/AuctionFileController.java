package controllers;

import models.Auction;
import models.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static controllers.UserFileController.splitLine;

public class AuctionFileController {

    public static boolean writeAuctionToFile(Map<String, Auction> auctionMap, String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, false))) {
            for (Map.Entry<String, Auction> map : auctionMap.entrySet()) {
                writer.println(map.getKey() + ";;" + map.getValue());
            }
            writer.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Map<String, Auction> readAuctionsFromDataBaseFile(String filePath) {
        File file = new File(filePath);

        Map<String, Auction> auctionMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentLine;
            while (true) {
                currentLine = reader.readLine();
                if (currentLine == null) {
                    reader.close();
                    break;
                }
                Auction auction = new Auction(splitLine(currentLine)[1], Integer.parseInt(splitLine(currentLine)[2]), splitLine(currentLine)[3], new User(splitLine(currentLine)[4], splitLine(currentLine)[5]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return auctionMap;
    }

    public static String[] splitLine(String line) {

        return line.split(";;");
    }
}