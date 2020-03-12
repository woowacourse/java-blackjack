package view;

import domain.gamer.AllGamers;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printInitialCards(AllGamers allGamers) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러와 ");
        stringBuilder.append(parsePlayersName(allGamers.getPlayers()));
        stringBuilder.append("에게 2장의 카드를 나누었습니다.\n");
        stringBuilder.append(parseDealerInitialState(allGamers.getDealer()));
        stringBuilder.append(parseGamersState(allGamers.getPlayers()));

        System.out.println(stringBuilder.toString());
    }

    private static String parsePlayersName(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    private static String parseDealerInitialState(Dealer dealer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(dealer.getName());
        stringBuilder.append(": ");

        String dealerInitialCard = dealer.getCardsOnHand()
                .getOneCard()
                .toString();
        stringBuilder.append(dealerInitialCard);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public static void printGamerState(Gamer gamer) {
        System.out.println(parseGamerState(gamer));
    }

    private static String parseGamersState(List<Player> players) {
        return players
                .stream()
                .map(OutputView::parseGamerState)
                .collect(Collectors.joining("\n"));
    }

    private static String parseGamerState(Gamer gamer) {
        return gamer.getName() + ": " + gamer.getCardsOnHand().toString();
    }

    public static void printCanNotDrawMessage() {
        System.out.println("더이상 뽑을 수 없습니다.");
    }

    public static void printDealerCanDrawMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

}
