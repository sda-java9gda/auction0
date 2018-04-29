package models;

public class Auction {
    private String auctionName;
    private int price;
    private String auctionDescription;
    private int counter =0;
    private boolean isFinished;
    private User user;


    public Auction(String auctionName,int price, String auctionDescription, boolean isFinished, User user) {
        this.price = price;
        this.auctionName = auctionName;
        this.auctionDescription = auctionDescription;
        this.isFinished = isFinished;
        this.user = user;
    }

    public Auction(String auctionName, int price, String auctionDescription, User user) {
        this.auctionName = auctionName;
        this.price = price;
        this.auctionDescription = auctionDescription;
        this.user = user;
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

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getAuctionDescription() {
        return auctionDescription;
    }

    public void setAuctionDescription(String auctionDescription) {
        this.auctionDescription = auctionDescription;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return    auctionName+ ";;"
                 +  price+ ";;" +
                 auctionDescription + ";;" +
                ";;" + user ;
    }
}
