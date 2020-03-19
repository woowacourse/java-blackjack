package view;

import domain.card.Card;
import domain.gamer.AbstractGamer;
import domain.gamer.Dealer;
import domain.gamer.Name;
import domain.gamer.Player;
import domain.result.Result;
import domain.result.score.Score;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class OutputView {
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
                .map(Name::getValue)
                .collect(joining(", "));
    }

    private static String parseGamerInitialState(AbstractGamer gamer) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(gamer.getName().getValue());
        stringBuilder.append(": ");
        stringBuilder.append(parseCards(gamer.openInitialCards()));
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
        return gamer.getName().getValue() + ": " + parseCards(gamer.openAllCards());
    }

    public static void printDealerCanDrawMore(AbstractGamer dealer) {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printScore(AbstractGamer gamer, Score score) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(parseGamerState(gamer));
        stringBuilder.append(" - 결과: ");
        stringBuilder.append(score.getValue());

        System.out.println(stringBuilder.toString());
    }

    public static void printResults(List<Result> results) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("## 최종 수익");
        results.forEach(result -> stringBuilder.append(parseResult(result)));

        System.out.println(stringBuilder.toString());
    }

    public static String parseResult(Result result) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(result.getGamerName().getValue());
        stringBuilder.append(": ");
        stringBuilder.append((int) result.getBettingMoney().getValue());
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    public static void printEmptyLine() {
        System.out.println();
    }
}
