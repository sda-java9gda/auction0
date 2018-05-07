package controllers;

import exceptions.AuctionAlreadyExist;
import exceptions.NoSuchAuctionException;
import exceptions.YouAreNotTheOwnerException;
import exceptions.YouDoNotHaveAnyAuctions;
import models.Auction;
import models.User;
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

    public void addAuction(String auctionName, int price, String auctionDescription, String userLogin, String filePath, Map<String, Auction> auctionMap) throws AuctionAlreadyExist {
        Auction auction = new Auction(auctionName, price, auctionDescription, userLogin);
        if (!isAuctionExist(auctionName, auctionMap)) {
            auctionMap.put(auctionName, auction);
            AuctionFileController.writeAuctionToDataAuctionFile(auction, filePath);
            return;
        }
        throw new AuctionAlreadyExist();
    }

    public void removeAuction(Auction auction, String filepath, Map<String, Auction> auctionMap) throws NoSuchAuctionException {
        if (isAuctionExist(auction.getAuctionName(), auctionMap)) {
            auctionMap.remove(auction.getAuctionName());
            AuctionFileController.removeAuctionFromFile(auction, filepath);
            return;
        }
        throw new NoSuchAuctionException();
    }

    public boolean isAuctionExist(String auctionName, Map<String, Auction> auctionMap) {
        return auctionMap.containsKey(auctionName);
    }

    public boolean makeOffer(int price, Auction auction) {
        auction.setPrice(price);
        auction.setCounter(auction.getCounter() + 1);
        return auction.getCounter() == 3;
    }

    public void showAllAuction(Map<String, Auction> auctionMap) {
        int i = 1;
        for (Map.Entry<String, Auction> map : auctionMap.entrySet()) {
            System.out.println(i + ") " + map.getValue().toString());
            i++;
        }
    }

    public void showAuctionsForUser(Map<String, Auction> auctionMap, User user) throws YouDoNotHaveAnyAuctions {
        List<Auction> auctionList= auctionMap.values().stream()
                .filter(auction -> auction.getUserLogin().equals(user.getLogin()))
                .collect(Collectors.toList());
        int i=0;
        for (Auction auction : auctionList) {
            i++;
            System.out.println(i +") " + auction);
        }
        if (i==0) throw new YouDoNotHaveAnyAuctions();
    }

    public Auction chooseAuctionToBid(String auctionName, Map<String, Auction> auctionMap) throws NoSuchAuctionException{
        if (isAuctionExist(auctionName,auctionMap)) return auctionMap.get(auctionName);
        throw new NoSuchAuctionException();
    }

    public void isUserOwnAuction(String userLogin, String auctionName, Map<String, Auction> auctionMap) throws YouAreNotTheOwnerException {
        if (auctionMap.get(auctionName).getUserLogin().equalsIgnoreCase(userLogin)) {
            return;
        }
        throw new YouAreNotTheOwnerException();
    }
    public boolean isPriceBigger(Auction auction,int price){
        if (auction.getPrice()<price) return true;
        return false;
    }
}