package controllers;

import models.Auction;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AuctionFileController {

    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static boolean writeAuctionToDataAuctionFile(Auction auction, String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            writer.println(auction.toString());
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
                Auction auction = new Auction(splitLine(currentLine)[0], Integer.parseInt(splitLine(currentLine)[1]), splitLine(currentLine)[2], splitLine(currentLine)[3]);
                auctionMap.put(auction.getAuctionName(), auction);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return auctionMap;
    }

    public static boolean removeAuctionFromFile(Auction auction, String filePath) {
        try {
            File file = new File(filePath);
            File tempFile = new File(file.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter printer = new PrintWriter(new FileWriter(tempFile));

            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                if (!splitLine(currentLine)[0].equalsIgnoreCase(auction.getAuctionName())) {
                    printer.println(currentLine);
                    printer.flush();
                }
            }
            printer.close();
            reader.close();
            if (!file.delete()) {
                return false;
            }

            if (!tempFile.renameTo(file)) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String[] splitLine(String line) {
        return line.split(";");
    }
}