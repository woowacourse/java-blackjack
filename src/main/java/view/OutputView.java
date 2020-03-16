package view;

import domain.card.Card;
import domain.gamer.AbstractGamer;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.result.WinLose;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    public static void printEmptyLine() {
        System.out.println();
    }

    public static void printInitialCards(Dealer dealer, List<Player> players) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러와 ");
        stringBuilder.append(parsePlayerNames(players));
        stringBuilder.append("에게 2장의 카드를 나누었습니다.\n");
        stringBuilder.append(parseGamerInitialState(dealer));
        players.forEach(player -> stringBuilder.append(parseGamerInitialState(player)));

        System.out.println(stringBuilder.toString());
    }

    private static String parsePlayerNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(joining(", "));
    }

    private static String parseGamerInitialState(AbstractGamer gamer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gamer.getName());
        stringBuilder.append(": ");
        stringBuilder.append(parseCards(gamer.showInitialCards()));
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private static String parseCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(joining(", "));
    }

    public static void printGamerState(AbstractGamer gamer) {
        System.out.println(parseGamerState(gamer));
    }

    private static String parseGamerState(AbstractGamer gamer) {
        return gamer.getName() + ": " + parseCards(gamer.showAllCards());
    }

    public static void printCanNotDrawMessage(Player player) {
        System.out.println(player.getName() + "는 더이상 뽑을 수 없습니다.");
    }

    public static void printDealerCanDrawMore() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScore(AbstractGamer gamer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(parseGamerState(gamer));
        stringBuilder.append(" - 결과: ");
        stringBuilder.append(gamer.calculateScore().getValue());
        stringBuilder.append("\n");

        System.out.println(stringBuilder.toString());
    }

    public static void printResults(Map<WinLose, Integer> dealerWinLoses, List<Player> players, List<WinLose> playerWinLoses) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("##최종승패\n");
        stringBuilder.append(parseDealerWinLosesToString(dealerWinLoses));
        stringBuilder.append(parsePlayerWinLosesToString(players, playerWinLoses));

        System.out.println(stringBuilder.toString());
    }

    private static String parseDealerWinLosesToString(Map<WinLose, Integer> dealerWinLoses) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("딜러: ");
        stringBuilder.append(parseNullToZero(dealerWinLoses.get(WinLose.WIN)));
        stringBuilder.append(WinLose.WIN.getValue());
        stringBuilder.append(parseNullToZero(dealerWinLoses.get(WinLose.LOSE)));
        stringBuilder.append(WinLose.LOSE.getValue());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    private static int parseNullToZero(Integer input) {
        if (input == null) {
            return 0;
        }

        return input;
    }

    private static String parsePlayerWinLosesToString(List<Player> players, List<WinLose> playerWinLoses) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < players.size(); i++) {
            stringBuilder.append(players.get(i).getName());
            stringBuilder.append(": ");
            stringBuilder.append(playerWinLoses.get(i).getValue());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
