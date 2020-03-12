package view;

import domain.gamer.AllGamers;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
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

    private static String parseGamersState(List<Player> players) {
        return players
                .stream()
                .map(gamer -> gamer.getName() + ": " + gamer.getCardsOnHand().toString())
                .collect(Collectors.joining("\n"));
    }
}
