package views;

import models.Auction;

public class AuctionView {
    public static void printAuctionRemoved (String auctionName){
        System.out.println("Auction has been deleted " + auctionName);
    }
    public static void printAddAuction (String auctionName){
        System.out.println("Auction has been add " + auctionName);
    }
    public static void printAuctionAlreadyExist (String auctionName){
        System.out.println("Auction is already exist " + auctionName);
    }
    public static void printNoAuction (String auctionName){
        System.out.println("No auction " + auctionName);
    }
    public static void printCongrats (String auctionName){
        System.out.println("Congratulations, you win " + auctionName);
    }

}
