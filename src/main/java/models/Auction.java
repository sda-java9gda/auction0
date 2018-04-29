package models;

public class Auction {
    private String auctionName;
    private int price;
    private String auctionDescription;
    private int counter =0;
    private boolean isFinished;
    private String userLogin;


    public Auction(String auctionName,int price, String auctionDescription, boolean isFinished, String userLogin) {
        this.price = price;
        this.auctionName = auctionName;
        this.auctionDescription = auctionDescription;
        this.isFinished = isFinished;
        this.userLogin = userLogin;
    }

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

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public String toString() {
        return    auctionName+ ";;"
                 +  price+ ";;" +
                 auctionDescription +
                ";;" + userLogin;
    }
}
