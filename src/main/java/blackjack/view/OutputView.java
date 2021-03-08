package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printCardsOf(Player player) {
        System.out.println(cardsOf(player));
    }

    public static void printParticipantsCards(Dealer dealer, Players players) {
        System.out.printf("\n%s와 %s에게 2장의 나누었습니다.\n", dealer.getName(), playerNames(players));
        System.out.println(cardsOf(dealer));
        for (Player player : players.values()) {
            System.out.println(cardsOf(player));
        }
    }

    private static String cardsOf(Playable playable) {
        return playable.getName() + cardsToString(playable.getUnmodifiableCards());
    }

    private static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", ", "카드: ", ""));
    }

    private static String playerNames(Players players) {
        return players.values().stream()
                .map(Playable::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerGetCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printResult(Dealer dealer, Players players) {
        printParticipantsResults(dealer, players);
        printWinOrLose(dealer, players);
    }

    private static void printWinOrLose(Dealer dealer, Players players) {
        System.out.println("\n## 최종 승패");
        System.out.println(dealer.getName() + ": " + OutcomesOfDealer(dealer.calculateOutcomes(players)));
        for (Player player : players.values()) {
            System.out.println(player.getName() + ": " + player.calculateOutcome(dealer).getWord());
        }
    }

    private static String OutcomesOfDealer(Map<Outcome, Integer> dealerOutcomes) {
        return dealerOutcomes.get(Outcome.WIN) + "승 " + dealerOutcomes.get(Outcome.LOSE) + "패 " + dealerOutcomes.get(Outcome.DRAW) + "무";
    }

    private static void printParticipantsResults(Dealer dealer, Players players) {
        System.out.println(resultOf(dealer));
        for (Player player : players.values()) {
            System.out.println(resultOf(player));
        }
    }

    private static String resultOf(Playable playable) {
        return cardsOf(playable) + " - 결과: " + playable.sumCardsForResult();
    }
}
