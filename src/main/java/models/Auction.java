package models;

public class Auction {
    private String auctionName;
    private int price;
    private String auctionDescription;
    private int counter = 0;
    private String userLogin;

    public Auction(String auctionName, int price, String auctionDescription, String userLogin) {
        this.auctionName = auctionName;
        this.price = price;
        this.auctionDescription = auctionDescription;
        this.userLogin = userLogin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public String toString() {
        return auctionName + ";" +
                price + ";" +
                auctionDescription + ";" +
                userLogin;
    }
}