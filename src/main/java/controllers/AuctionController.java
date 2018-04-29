package controllers;

import models.Auction;
import models.User;
import views.AuctionView;
import views.UserView;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AuctionController {
    public Map<String, Auction> getMapOfAuctions(String filepath) {
        Map<String, Auction> auctionMap = AuctionFileController.readAuctionsFromDataBaseFile(filepath);
        if (auctionMap != null) {
            return auctionMap;
        }
        return auctionMap = new HashMap<>();
    }

    public boolean addAuction(int price, String auctionName, String auctionDescription, User user, Map<String, Auction> auctionMap) {
        Auction auction = new Auction(auctionName, price,auctionDescription,user);
        if (!isAuctionExist(auctionName, auctionMap)) {
            auctionMap.put(auctionName, auction);
            return true;
        }
        return false;
    }

    public boolean removeAuction(Auction auction, Map<String, Auction> auctionMap) {
        if (isAuctionExist(auction.getAuctionName(), auctionMap)) {
            auctionMap.remove(auction);
            return true;
        }
        return false;
    }

    public boolean isAuctionExist(String auctionName, Map<String, Auction> auctionMap) {
        return auctionMap.containsKey(auctionName);
    }
    public void saveAuctionsMap(Map<String, Auction> auctionMap, String filePath){
        try {
            AuctionFileController.writeAuctionToFile(auctionMap,filePath );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void makeOffer (int price, Auction auction){
        auction.setPrice(price);
        auction.setCounter(auction.getCounter()+1);
        if(auction.getCounter()==3){
            auction.setFinished(true);
        }
        AuctionView.printCongrats(auction.getAuctionName());
    }
}
