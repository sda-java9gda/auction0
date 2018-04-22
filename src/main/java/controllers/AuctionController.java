package controllers;

import models.Auction;
import models.AuctionRegistry;
import models.User;

public class AuctionController {
    public static boolean addAuction(Auction auction, User user){
        AuctionRegistry.getInstanceOfAuction(addAuction(auction, user));
    return true;
    }

    public static boolean removeAuction(Auction auction, User user){
        AuctionRegistry.getInstanceOfAuction(removeAuction(auction,user ));
        return true;
    }
}
