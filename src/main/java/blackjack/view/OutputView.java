package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
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

    private static String cardsOf(Participant participant) {
        return participant.getName() + cardsToString(participant.getUnmodifiableCards());
    }

    private static String cardsToString(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", ", "카드: ", ""));
    }

    private static String playerNames(Players players) {
        return players.values().stream()
                .map(Participant::getName)
                .collect(Collectors.joining(", "));
    }

    public static void printDealerGetCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public static void printResult(Dealer dealer, Players players) {
        printParticipantsResults(dealer, players);
        printProfits(dealer, players);
    }

    private static void printProfits(Dealer dealer, Players players) {
        System.out.println("\n## 최종 수익");
        double dealerProfit = 0;
        for (Player player : players.values()) {
            dealerProfit -= player.calculateProfitFromState(dealer);
        }
        System.out.println(dealer.getName() + ": " + dealerProfit);
        for (Player player : players.values()) {
            System.out.println(player.getName() + ": " + player.calculateProfitFromState(dealer));
        }
    }

    private static void printParticipantsResults(Dealer dealer, Players players) {
        System.out.println(resultOf(dealer));
        for (Player player : players.values()) {
            System.out.println(resultOf(player));
        }
    }

    private static String resultOf(Participant participant) {
        return cardsOf(participant) + " - 결과: " + participant.sumCardsForResult();
    }
}
