import controllers.AuctionController;
import controllers.AuctionFileController;
import controllers.UserController;
import controllers.UserFileController;
import exceptions.*;
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
        Map<String, Auction> oldAuctionsMap = new HashMap<>();
        final String USERS_FILEPATH = "src/main/resources/usersDataBase.txt";
        final String AUCTIONS_FILEPATH = "src/main/resources/auctionsDataBase.txt";
        final String OLD_AUCTIONS_FILEPATH = "src/main/resources/oldAuctionsDataBase.txt";
        User user = null;
        Auction auction = null;
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
                        uc.removeUser(login, USERS_FILEPATH, usersMap);
                        UserView.accountRemoved();
                    } catch (NoSuchUserException e) {
                        UserView.wrongPasswordOrLogin();
                    } finally {
                        state = States.INIT;
                    }
                    break;
                }
                case LOGGED: {
                    OtherViews.loggedView();
                    if (!AuctionFileController.isFileExist(AUCTIONS_FILEPATH)) {
                        AuctionFileController.writeAuctionToDataAuctionFile(auction, AUCTIONS_FILEPATH);
                    }
                    auctionsMap = ac.getMapOfAuctions(AUCTIONS_FILEPATH);
                    oldAuctionsMap = ac.getMapOfAuctions(OLD_AUCTIONS_FILEPATH);
                    decision = sc.nextLine();
                    switch (decision) {
                        case "1": {
                            AuctionView.printAllAuctions();
                            ac.showAllAuction(auctionsMap);
                            state = States.LOGGED;
                            break;
                        }
                        case "2": {
                            AuctionView.enterActionName();
                            String auctionName = sc.nextLine();
                            AuctionView.enterActionDescription();
                            String auctionDescription = sc.nextLine();
                            AuctionView.enterActionPrice();
                            int price = sc.nextInt();
                            try {
                                ac.addAuction(auctionName, price, auctionDescription, user.getLogin(), AUCTIONS_FILEPATH, auctionsMap);
                                AuctionView.auctionAdded();
                            } catch (AuctionAlreadyExist ex) {
                                AuctionView.auctionAlreadyExist();
                            } finally {
                                state = States.LOGGED;
                                sc.nextLine();
                            }
                            break;
                        }
                        case "3": {
                            AuctionView.enterActionName();
                            String auctionName = sc.nextLine();
                            try {
                                ac.isUserOwnAuction(user.getLogin(), auctionName, auctionsMap);
                                ac.removeAuction(auctionsMap.get(auctionName), AUCTIONS_FILEPATH, auctionsMap);
                                AuctionView.auctionRemoved();
                            } catch (NoSuchAuctionException ex) {
                                AuctionView.auctionDoNotExist();
                            } catch (YouAreNotTheOwnerException ex) {
                                AuctionView.youAreNotOwner();
                            } finally {
                                state = States.LOGGED;
                            }
                            break;
                        }
                        case "4": {
                            AuctionView.yourAuctionsList();
                            try {
                                ac.showAuctionsForUser(auctionsMap, user);
                            } catch (YouDoNotHaveAnyAuctions ex) {
                                AuctionView.doNotHaveAnyAuction();
                            } finally {
                                state = States.LOGGED;
                            }
                            break;
                        }
                        case "5": {
                            AuctionView.enterActionName();
                            String auctionName = sc.nextLine();
                            int biddingPrice;
                            boolean isWorking = true;
                            try {
                                auction = ac.chooseAuctionToBid(auctionName, auctionsMap);
                                while (isWorking) {
                                    AuctionView.bidPrice();
                                    biddingPrice = sc.nextInt();
                                    if (!ac.isPriceBigger(auction, biddingPrice)) {
                                        AuctionView.priceIsTooLow();
                                        continue;
                                    }
                                    if (ac.makeOffer(biddingPrice, auction)) {
                                        AuctionView.printCongrats(auctionName, biddingPrice);
                                        try {
                                            ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), user.getLogin(), OLD_AUCTIONS_FILEPATH, oldAuctionsMap);
                                            ac.removeAuction(auctionsMap.get(auctionName), AUCTIONS_FILEPATH, auctionsMap);
                                        } catch (AuctionAlreadyExist ignored) {
                                        } finally {
                                            isWorking = false;
                                        }
                                    }
                                }
                            } catch (NoSuchAuctionException e) {
                                AuctionView.auctionDoNotExist();
                            } finally {
                                sc.nextLine();
                                state = States.LOGGED;
                            }
                            break;
                        }
                        case "0": {
                            UserView.loggedOut();
                            state = States.INIT;
                            break;
                        }
                    }
                }
                break;
                case EXIT:
                    UserView.exitProgram();
                    break;
            }
        } while (state != States.EXIT);
    }
}