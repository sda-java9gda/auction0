import controllers.AuctionController;
import controllers.AuctionFileController;
import controllers.UserController;
import controllers.UserFileController;
import exceptions.IllegalCharException;
import exceptions.LoginTakenException;
import exceptions.NoSuchUserException;
import models.Auction;
import models.States;
import models.User;
import views.AuctionView;
import views.OtherViews;
import views.UserView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        States state = States.INIT;
        UserController uc = new UserController();
        AuctionController ac = new AuctionController();
        Map<String, User> usersMap = new HashMap<>();
        Map<String, Auction> auctionsMap = new HashMap<>();
        final String USERS_FILEPATH = "src/main/resources/usersDataBase.txt";
        final String AUCTIONS_FILEPATH = "src/main/resources/auctionsDataBase.txt";
        User user = null;
        if (!UserFileController.isFileExist(USERS_FILEPATH)) {
            try {
                UserFileController.writeUsersToFile(user, USERS_FILEPATH);
            } catch (NullPointerException ignored) {
            }
        }
        OtherViews.welcomeView();
        do {
            switch (state) {
                case INIT:
                    OtherViews.initView();
                    usersMap = uc.getMapOfUsers(USERS_FILEPATH);
                    String decision = sc.nextLine();
                    switch (decision) {
                        case ("1"):
                            state = States.LOGIN;
                            break;
                        case ("2"):
                            state = States.REGISTRATION;
                            break;
                        case ("3"):
                            state = States.REMOVING;
                            break;
                        case ("0"):
                            state = States.EXIT;
                            break;
                        default:
                            OtherViews.invalidCharacter();
                            state = States.INIT;
                            break;
                    }
                    break;
                case LOGIN: {
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();
                    try {
                        uc.isLoginAndPasswordCorrect(login, password, usersMap);
                        UserView.loggedIn(login);
                        state = States.LOGGED;
                        user = new User(login, password);
                    } catch (NoSuchUserException e) {
                        UserView.wrongPasswordOrLogin();
                        state = States.INIT;
                    }
                    break;
                }
                case REGISTRATION: {
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();
                    try {
                        uc.isPasswordContainsIllegalChars(password);
                        uc.addUser(login, password, USERS_FILEPATH, usersMap);
                        UserView.accountRegistered();
                    } catch (IllegalCharException ex) {
                        UserView.illegalChar();
                    } catch (LoginTakenException ex) {
                        UserView.loginIsTaken(login);
                    } finally {
                        state = States.INIT;
                    }
                    break;
                }
                case REMOVING: {
                    String login, password;
                    UserView.enterLogin();
                    login = sc.nextLine();
                    UserView.enterPassword();
                    password = sc.nextLine();
                    try {
                        uc.isLoginAndPasswordCorrect(login, password, usersMap);
                        uc.removeUser(login, password, USERS_FILEPATH, usersMap);
                        UserView.accountRemoved();
                    } catch (NoSuchUserException e) {
                        UserView.wrongPasswordOrLogin();
                    } finally {
                        state = States.INIT;
                    }
                    break;
                }
                case LOGGED: {
                    System.out.println("Hello! What you want to do?");
                    System.out.println("Press \"1\"  to view the auctions");
                    System.out.println("Press \"2\"  to add auction");
                    System.out.println("Press \"3\"  to remove");
                    System.out.println("Press \"4\"  to view yours auctions");
                    System.out.println("Press \"5\"  to bid the auction");
                    System.out.println("Press \"0\"  to log out");
                    if (!AuctionFileController.isFileExist(AUCTIONS_FILEPATH)) {
                        AuctionFileController.writeAuctionToDataAuctionFile(auctionsMap, AUCTIONS_FILEPATH);
                    }
                    auctionsMap = ac.getMapOfAuctions(AUCTIONS_FILEPATH);

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
                            UserView.loggedOut();
                            state = States.INIT;
                            break;
                    }
                }
                break;
                case EXIT:
                    UserView.exitProgram();
                    break;
                case AUCTION_ADD: {
                    boolean addAuction;
                    String auctionName = sc.nextLine();
                    String auctionDescription = sc.nextLine();
                    int price = sc.nextInt();
                    addAuction = ac.addAuction(price, auctionName, auctionDescription, user.getLogin(), auctionsMap);
                    if (addAuction) {
                        AuctionView.printAddAuction(auctionName);
                        state = States.LOGGED;
                        AuctionFileController.writeAuctionToDataAuctionFile(auctionsMap, AUCTIONS_FILEPATH);
                    } else System.out.println("nie udalo sie dodac aukcji");

                }
                case AUCTION_lIST: {
                    ac.showAllAuction(auctionsMap);
                    state = States.LOGGED;
                }
                case AUCTION_LIST_FOR_USER: {
                    ac.showAuctionsForUser(auctionsMap, user);
                    state = States.LOGGED;
                }
                case AUCTION_BIDDING: {
                    String auctionName = sc.nextLine();
                    Auction auction;
                    int biddingPrice;
                    boolean isWorking = true;
                    if (ac.isAuctionExist(auctionName, auctionsMap)) {
                        auction = ac.chooseAuctionToBid(auctionName, auctionsMap);

                        while (isWorking) {
                            System.out.println("podbij cene: ");
                            biddingPrice = sc.nextInt();
                            if (ac.makeOffer(biddingPrice, auction)) {

                                System.out.println("WYGRALES AUKCJE");
                                isWorking = false;
                                state = States.LOGGED;
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
