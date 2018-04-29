import controllers.AuctionController;
import controllers.AuctionFileController;
import controllers.UserController;
import controllers.UserFileController;
import models.Auction;
import models.States;
import models.User;
import views.AuctionView;
import views.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        States state = States.INIT;
        UserController userController = new UserController();
        AuctionController auctionController = new AuctionController();
        Map<String, User> usersMap = new HashMap<>();
        Map<String, Auction> auctionMap = new HashMap<>();
        final String USERS_FILEPATH = "src/main/resources/usersDataBase.txt";
        final String AUCTION_FILEPATH = "src/main/resources/auctionsDataBase.txt";
        User user = null;
        do {
            switch (state) {
                case INIT:
                    System.out.println("Hello! What you want to do?");
                    System.out.println("Press \"1\"  to login the user");
                    System.out.println("Press \"2\"  to register the user");
                    System.out.println("Press \"0\"  to exit");
                    if (!UserFileController.isFileExist(USERS_FILEPATH)) {
                        UserFileController.writeUsersToDataBaseFile(usersMap, USERS_FILEPATH);
                    }
                    usersMap = userController.getMapOfUsers(USERS_FILEPATH);
                    String decision = sc.nextLine();
                    switch (decision) {

                        case ("1"):
                            state = States.LOGIN;
                            break;
                        case ("2"):
                            state = States.REGISTRATION;
                            break;
                        case ("0"):
                            state = States.EXIT;
                            break;
                        default:
                            System.out.println("Invalid character!");
                            state = States.INIT;
                            break;
                    }
                    break;
                case REGISTRATION: {
                    boolean isRegistered;
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();

                    isRegistered = userController.addUser(login, password, usersMap);
                    if (isRegistered) {
                        state = States.LOGIN;
                        UserView.accountRegistered();
                    } else {
                        state = States.INIT;
                        UserView.loginIsTaken(login);
                    }
                    break;
                }
                case LOGIN: {
                    boolean isLogged;
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();

                    isLogged = userController.logInUser(login, password, usersMap);
                    if (isLogged) {
                        UserView.welcomeUser(login);

                        state = States.LOGGED;
                        user = new User(login, password);
                    } else {
                        UserView.wrongPasswordOrLogin();
                        state = States.LOGIN;
                    }
                    break;
                }
                case LOGGED:
                    System.out.println("Hello! What you want to do?");
                    System.out.println("Press \"1\"  to view the auctions");
                    System.out.println("Press \"2\"  to add auction");
                    System.out.println("Press \"3\"  to remove");
                    System.out.println("Press \"4\"  to view yours auctions");
                    System.out.println("Press \"5\"  to bid the auction");
                    System.out.println("Press \"0\"  to log out");
                    if (!AuctionFileController.isFileExist(AUCTION_FILEPATH)) {
                        AuctionFileController.writeAuctionToDataAuctionFile(auctionMap, AUCTION_FILEPATH);
                    }
                    auctionMap = auctionController.getMapOfAuctions(AUCTION_FILEPATH);

                    decision = sc.nextLine();
                    switch (decision) {
                        case "1":
                            state = state.AUCTION_lIST;
                            break;
                        case "2":
                            state = state.AUCTION_ADD;
                            break;
                        case "3":
                            state = state.AUCTION_REMOVE;
                            break;
                        case "4":
                            state = state.AUCTION_LIST_FOR_USER;
                            break;
                        case "5":
                            state = state.AUCTION_BIDDING;
                            break;
                        case "0":
                            UserView.logoutMessage();
                            state = States.INIT;
                            break;
                    }
                    break;
                case EXIT:
                    userController.saveUsersMapToFile(usersMap, USERS_FILEPATH);
                    UserView.exitProgramMessage();
                    break;
                case AUCTION_ADD: {
                    boolean addAuction;
                    String auctionName = sc.nextLine();
                    String auctionDescription = sc.nextLine();
                    int price = sc.nextInt();
                    addAuction = auctionController.addAuction(price, auctionName, auctionDescription, user.getLogin(), auctionMap);
                    if (addAuction) {
                        AuctionView.printAddAuction(auctionName);
                        state = States.LOGGED;
                        AuctionFileController.writeAuctionToDataAuctionFile(auctionMap, AUCTION_FILEPATH);
                    } else System.out.println("nie udalo sie dodac aukcji");

                }
                case AUCTION_lIST: {
                    auctionController.showAllAuction(auctionMap);
                    state = States.LOGGED;
                }
                case AUCTION_LIST_FOR_USER: {
                    auctionController.showAuctionsForUser(auctionMap, user);
                    state = States.LOGGED;
                }
                case AUCTION_BIDDING: {
                    String auctionName = sc.nextLine();
                    Auction auction;
                    int biddingPrice;
                    boolean isWorking = true;
                    if (auctionController.isAuctionExist(auctionName, auctionMap)) {
                        auction = auctionController.chooseAuctionToBid(auctionName, auctionMap);

                        while (isWorking) {
                            System.out.println("podbij cene: ");
                            biddingPrice = sc.nextInt();
                            if (auctionController.makeOffer(biddingPrice, auction)) {

                                System.out.println("WYGRALES AUKCJE");
                                isWorking=false;
                                state=States.LOGGED;
                            }
                        }
                    } else {
                        System.out.println("bledna nazwa aukcji ");
                        state = States.LOGGED;
                    }

                }
            }
        } while (state != States.EXIT);
    }
}
