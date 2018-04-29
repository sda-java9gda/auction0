package controllers;

import models.Auction;
import models.User;
import views.AuctionView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AuctionController {

    public Map<String, Auction> getMapOfAuctions(String filepath) {
        Map<String, Auction> auctionMap = AuctionFileController.readAuctionsFromDataBaseFile(filepath);
        if (auctionMap != null) {
            return auctionMap;
        }
        return auctionMap = new HashMap<>();
    }

    public boolean addAuction(int price, String auctionName, String auctionDescription, String userLogin, Map<String, Auction> auctionMap) {
        Auction auction = new Auction(auctionName, price,auctionDescription,userLogin);
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
            AuctionFileController.writeAuctionToDataAuctionFile(auctionMap,filePath );
    }
//    public void makeOffer (int price, Auction auction){
//        auction.setPrice(price);
//        auction.setCounter(auction.getCounter()+1);
//        if(auction.getCounter()==3){
//            auction.setFinished(true);
//        }
//        AuctionView.printCongrats(auction.getAuctionName());
//    }
        public boolean makeOffer (int price, Auction auction){
        auction.setPrice(price);
        auction.setCounter(auction.getCounter()+1);
        if(auction.getCounter()==3){
            return true;
        }
        return false;
    }


    public void showAllAuction (Map<String, Auction> auctionMap){
//            List auctionList = new ArrayList();
        for (Map.Entry<String, Auction> map : auctionMap.entrySet()) {
            System.out.println(map.getValue().toString());
        }
//        return auctionList;
//        return auctionList= auctionMap.entrySet().stream()
//                .collect(Collectors.toList());
    }
    public List<Auction> showAuctionsForUser(Map<String,Auction> auctionMap,User user){
        return auctionMap.values().stream()
                .filter(auction -> auction.getUserLogin().equals(user.getLogin()))
                .collect(Collectors.toList());
    }

    public Auction chooseAuctionToBid(String auctionName,Map<String,Auction> auctionMap ) {
        return auctionMap.get(auctionName);
    }
    public boolean isAuctionMapContainsAuction(String auctionName,Map<String,Auction> auctionMap){
        if (auctionMap.containsKey(auctionName)) return true;
        return false;
    }
}
