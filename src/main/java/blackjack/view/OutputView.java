package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Player;
import blackjack.domain.result.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String SEPARATOR = ": ";

    private OutputView() {
    }

    public static void printParticipate(List<Player> players) {
        String playerLine = players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println("딜러와 " + playerLine + "에게 2장을 나누었습니다.");
    }

    public static void printCards(Participant participant) {
        List<Card>  cards = participant.cards();
        List<String> result = new ArrayList<>();
        for (Card card : cards) {
            result.add(card.toString());
        }
        System.out.println(participant.getName() + "카드: " + String.join(DELIMITER, result));
    }

    public static void printCards(Participant participant, int showCardCount) {
        List<Card> cards = participant.cards(showCardCount);
        List<String> result = new ArrayList<>();
        for (Card card : cards) {
            result.add(card.toString());
        }
        System.out.println(participant.getName() + "카드: " + String.join(DELIMITER, result));
    }

    public static void printCardsWithScore(Participant participant) {
        List<Card> cards = participant.cards();
        List<String> output = new ArrayList<>();
        for (Card card : cards) {
            output.add(card.toString());
        }
        System.out.println(participant.getName()
                + "카드: "
                + String.join(DELIMITER, output)
                + " - 결과: "
                + participant.getScore());
    }

    public static void printOneMoreCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printDealerResult(Dealer dealer) {
        System.out.print(NEW_LINE);
        System.out.println("## 최종 승패");
        System.out.print("딜러" + SEPARATOR);

        if (dealer.countOfResult(Result.WIN) > 0) {
            System.out.print(dealer.countOfResult(Result.WIN) + "승 ");
        }
        if (dealer.countOfResult(Result.DRAW) > 0) {
            System.out.print(dealer.countOfResult(Result.DRAW) + "무 ");
        }
        if (dealer.countOfResult(Result.LOSE) > 0) {
            System.out.print(dealer.countOfResult(Result.LOSE) + "패 ");
        }
        System.out.print(NEW_LINE);
    }

    public static void printPlayerResult(List<Player> players, List<Result> results) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()
                    + SEPARATOR
                    + results.get(i).getResultName());
        }
    }

    public static void printProfit() {
        System.out.print(NEW_LINE);
        System.out.println("## 최종 수익");
    }

    public static void printProfitDealer(Dealer dealer, List<Player> players) {
        System.out.println("딜러: " + dealer.profit(players));
    }

    public static void printProfitPlayers(Dealer dealer, List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getName()
                    + ": "
                    + players.get(i).profit(dealer.getPlayerResult(i)));
        }
    }

    public static void printNewLine() {
        System.out.print(NEW_LINE);
    }
}
