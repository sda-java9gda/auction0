package models;

import exceptions.AuctionNotFoundExeption;
import exceptions.DuplicateFoundException;

import java.util.ArrayList;

public class AuctionRegistry {
    private static AuctionRegistry instance = null;

    public static AuctionRegistry getInstanceOfAuction() {
        if (instance == null) {
            instance = new AuctionRegistry();
        }
        return instance;
    }

    private ArrayList<Auction> auctions;

    public AuctionRegistry() {
        this.auctions = models.FileHandler.loadAuction("auctions");
    }

    public Auction findAuction(String name) throws AuctionNotFoundExeption {
        for (Auction auction : this.auctions) {
            if (auction.getAuctionName().equals(name)) {
                return auction;
            }
        }
        throw  new AuctionNotFoundExeption();
    }
    public Auction findAuctionByLogin (String login) throws AuctionNotFoundExeption {
        for (Auction auction:this.auctions) {
            if (auction.getUser().getLogin().equals(login)){
                return auction;
            }

        }throw new AuctionNotFoundExeption();
    }
    public void addAuction (int auctionPrice, String auctionName, String auctiionDescription, User user )throws DuplicateFoundException{
        for (Auction auction:auctions) {
            if (auction.getAuctionName().equals(auctionName)){
                throw new DuplicateFoundException();
            }

        }auctions.add(new Auction(auctionPrice,auctionName,auctiionDescription,user));
    }
    public void saveAuction(){
models.FileHandler.save(this.auctions, "auctions" );
    }

}


