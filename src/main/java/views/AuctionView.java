package views;

public class AuctionView {
    public static void printAllAuctions() {
        System.out.println("List of all our auctions: ");
    }

    public static void enterActionName() {
        System.out.println("Enter auction name: ");
    }

    public static void enterActionDescription() {
        System.out.println("Enter auction description: ");
    }

    public static void enterActionPrice() {
        System.out.println("Enter auction price: ");
    }

    public static void auctionRemoved() {
        System.out.println("Auction has been deleted.\n");
    }

    public static void auctionAdded() {
        System.out.println("Auction has been add.\n");
    }

    public static void auctionAlreadyExist() {
        System.out.println("The auction with this name already exists.\n");
    }

    public static void auctionDoNotExist() {
        System.out.println("Such an auction does not exist.\n");
    }

    public static void printCongrats(String auctionName, int price) {
        System.out.println("Congratulations, you bought " + auctionName + " for " + price + " PLN.\n");
    }

    public static void youAreNotOwner() {
        System.out.println("You are not the owner of this auction.\n");
    }

    public static void yourAuctionsList() {
        System.out.println("List of your auctions: ");
    }

    public static void doNotHaveAnyAuction() {
        System.out.println("You do not have any auctions.\n");
    }

    public static void bidPrice() {
        System.out.println("Bid price: ");
    }

    public static void priceIsTooLow() {
        System.out.println("the price is too low.\n");
    }
}