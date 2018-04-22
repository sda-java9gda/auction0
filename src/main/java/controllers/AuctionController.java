package controllers;

import models.Auction;
import models.AuctionRegistry;
import models.User;
import view.AuctionView;

public class AuctionController {
    public static boolean addAuction(Auction auction, User user){
        AuctionRegistry.getInstanceOfAuction(addAuction(auction, user));
        System.out.println(AuctionView.printAddSuccess(auction.getAuctionName()));
    return true;
    }

    public static boolean removeAuction(Auction auction, User user){
        AuctionRegistry.getInstanceOfAuction(removeAuction(auction,user ));
        System.out.println(AuctionView.printRemoveSuccess(auction.getAuctionName()));
        return true;
    }
}
