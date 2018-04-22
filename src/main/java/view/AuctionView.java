package view;

import models.Auction;

import java.util.List;

public class AuctionView {
    public static String printAuctions(List<Auction> auctionList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Auction auction : auctionList) {
            StringBuilder append = stringBuilder.append(auction.getAuctionName()).append("\n");
        }
        return stringBuilder.toString();

    }

    public static String printNotFound(String name) {
        return "Nie znaleziono aukcji" + name;
    }

    public static String printAddSuccess(String name) {
        return "Gratulacje, dodano aukcje " + name;
    }

    public static String printSaledSuccess (String name){
        return "Aukcja została zakończona" + name;
    }
    public static String printDuplicateFound (String name){
        return "Istnieje taka sama aukcja" + name;
    }
    public static String printRemoveSuccess (String name) {
        return "Ta aukcja " + name + " została usunięta";
    }
}
