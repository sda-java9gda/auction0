package models;

import java.math.BigDecimal;

public class Auction {
  BigDecimal auctionPrice;
    private String auctionName;
    private String auctionDescription;
    private int counter = 0;
    private boolean isFinished;
    private User user;

    public Auction(BigDecimal auctionPrice, String auctionName, String auctionDescription, boolean isFinished, User user) {
        this.auctionPrice = auctionPrice;
        this.auctionName = auctionName;
        this.auctionDescription = auctionDescription;
        this.isFinished = isFinished;
        this.user = user;
    }

    public BigDecimal getAuctionPrice() { return auctionPrice; }

    public void setAuctionPrice(BigDecimal auctionPrice) { this.auctionPrice = auctionPrice; }

    public String getAuctionName() { return auctionName; }

    public void setAuctionName(String auctionName) { this.auctionName = auctionName; }

    public String getAuctionDescription() {return auctionDescription; }

    public void setAuctionDescription(String auctionDescription) { this.auctionDescription = auctionDescription; }

    public int getCounter() { return counter; }

    public void setCounter(int counter) {this.counter = counter; }

    public boolean isFinished() { return isFinished; }

    public void setFinished(boolean finished) { isFinished = finished; }

    public User getUser() { return user; }

    public void setUser(User user) {this.user = user; }
}
