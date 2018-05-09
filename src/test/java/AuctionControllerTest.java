import controllers.AuctionController;
import exceptions.NoSuchAuctionException;
import exceptions.YouAreNotTheOwnerException;
import exceptions.YouDoNotHaveAnyAuctions;
import models.Auction;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class AuctionControllerTest {
    private AuctionController ac;
    private Map<String, Auction> auctionMap;
    private String filePath;
    private Auction auction;

    @Before
    public void setUp() {
        auction = new Auction("name", 1234, "description", "userName");
        ac = new AuctionController();
        auctionMap = new HashMap<>();
        filePath = "src/test/resources/TestsAuctions.txt";
    }

    @After
    public void cleanUp() throws Exception {
        Files.deleteIfExists(Paths.get(filePath));
    }

    //Testing getMapOfAuctions
    @Test
    public void getMapOfAuctionsShouldReturnMapThatContainsKey() throws Exception {
        Files.write(Paths.get(filePath), "name;1234;description;userName\n".getBytes(), StandardOpenOption.CREATE);

        auctionMap = ac.getMapOfAuctions(filePath);

        assertTrue(auctionMap.containsKey("name"));
    }

    @Test
    public void getMapOfAuctionsShouldReturnMapThatContainsAuction() throws Exception {
        Files.write(Paths.get(filePath), auction.toString().getBytes(), StandardOpenOption.CREATE);

        auctionMap = ac.getMapOfAuctions(filePath);
        auctionMap.put(auction.getAuctionName(), auction);

        assertTrue(auctionMap.containsValue(auction));
    }

    @Test
    public void getMapOfAuctionsShouldReturnMapThatDoNotContainsAuction() throws Exception {
        Files.write(Paths.get(filePath), "22;22;22;22".getBytes(), StandardOpenOption.CREATE);

        auctionMap = ac.getMapOfAuctions(filePath);

        assertFalse(auctionMap.containsValue(auction));
    }

    //Testing addAuction
    @Test
    public void shouldAddAuctionToMap() throws Exception {
        ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), auction.getUserLogin(), filePath, auctionMap);
        String expected = auction.toString();
        String actual = auctionMap.get(auction.getAuctionName()).toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldNotAddAuctionToMap() throws Exception {
        ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), auction.getUserLogin(), filePath, auctionMap);
        String expected = "11;11;11;11";
        String actual = auctionMap.get(auction.getAuctionName()).toString();

        assertThat(actual).isNotEqualTo(expected);
    }

    @Test
    public void shouldAddAuctionToFile() throws Exception {
        ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), auction.getUserLogin(), filePath, auctionMap);
        auctionMap = ac.getMapOfAuctions(filePath);

        assertThat(auctionMap).isNotEmpty();
    }

    //Testing removeAuction
    @Test
    public void shouldRemoveAuction() throws Exception {
        Files.write(Paths.get(filePath), auction.toString().getBytes(), StandardOpenOption.CREATE);
        auctionMap.put(auction.getAuctionName(), auction);

        ac.removeAuction(auction, filePath, auctionMap);
        assertFalse(auctionMap.containsKey(auction.getAuctionName()));
    }

    @Test(expected = NoSuchAuctionException.class)
    public void shouldRemoveAuctionThrowException() throws Exception {
        Files.write(Paths.get(filePath), auction.toString().getBytes(), StandardOpenOption.CREATE);
        auctionMap.put(auction.getAuctionName(), auction);
        Auction auction1 = new Auction("11", 11, "11", "11");

        ac.removeAuction(auction1, filePath, auctionMap);
        assertTrue(auctionMap.containsKey("name"));
    }

    //Testing isAuctionExist
    @Test
    public void isAuctionExistShouldBeTrue() throws Exception {
        ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), auction.getUserLogin(), filePath, auctionMap);
        boolean actual = ac.isAuctionExist(auction.getAuctionName(), auctionMap);

        assertTrue(actual);
    }

    @Test
    public void isAuctionExistShouldBeFalse() throws Exception {
        ac.addAuction(auction.getAuctionName(), auction.getPrice(), auction.getAuctionDescription(), auction.getUserLogin(), filePath, auctionMap);
        boolean actual = ac.isAuctionExist("11", auctionMap);

        assertFalse(actual);
    }

    //Testing makeOffer
    @Test
    public void shouldMakeOfferReturnTrue() {
        int price = 10000;
        boolean expected = false;
        for (int i = 0; i < 3; i++) {
            expected = ac.makeOffer(price, auction);
            price += 1000;
        }
        assertTrue(expected);
    }

    @Test
    public void shouldMakeOfferReturnFalse() {
        int price = 10000;
        boolean expected = false;
        for (int i = 0; i < 2; i++) {
            expected = ac.makeOffer(price, auction);
            price += 1000;
        }
        assertFalse(expected);
    }

    //Testing shouwAuctionsForUser
    @Test(expected = YouDoNotHaveAnyAuctions.class)
    public void shouldShowAuctionsForUserThrowException() throws Exception {
        User user = new User(auction.getUserLogin(), "1234");
        ac.showAuctionsForUser(auctionMap, user);
    }

    @Test
    public void shouldShowAuctionsForUser() throws Exception {
        User user = new User(auction.getUserLogin(), "1234");
        auctionMap.put(auction.getAuctionName(), auction);
        ac.showAuctionsForUser(auctionMap, user);
    }

    //Testing isUserOwnAuction
    @Test
    public void shouldUserOwnAuction() throws Exception {
        auctionMap.put(auction.getAuctionName(), auction);
        ac.isUserOwnAuction(auction.getUserLogin(), auction.getAuctionName(), auctionMap);
    }

    @Test(expected = YouAreNotTheOwnerException.class)
    public void shouldUserOwnAuctionThrowException() throws Exception {
        auctionMap.put(auction.getAuctionName(), auction);
        ac.isUserOwnAuction("tttt", auction.getAuctionName(), auctionMap);
    }

    @Test
    public void shoudPriceBeBigger() {
        int price = 100000;
        boolean expected = ac.isPriceBigger(auction, price);
        assertTrue(expected);
    }

    @Test
    public void shoudPriceBeLower() {
        int price = 10;
        boolean expected = ac.isPriceBigger(auction, price);
        assertFalse(expected);
    }
}