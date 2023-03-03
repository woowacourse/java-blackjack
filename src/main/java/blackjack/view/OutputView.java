package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Result;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    public static void printInitCard(List<Player> players, Set<Card> dealerCards) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        System.out.println(NEW_LINE + "딜러와 " + playerNames + "에게 2장을 나누었습니다.");

        printParticipantCards("딜러", dealerCards);
        System.out.println();
        for (Player player : players) {
            printParticipantCards(player.getName(), player.getCards());
            System.out.println();
        }
    }

    public static void printParticipantCards(String playerName, Set<Card> cards) {
        System.out.print(playerName + "카드: " + printCards(cards));
    }

    private static String printCards(Set<Card> cards) {
        return cards.stream()
                .map(OutputView::printCard)
                .collect(Collectors.joining(", "));
    }

    private static String printCard(Card card) {
        return card.getRank().getValue() + card.getSuit().getValue();
    }

    public static void printDealerReceiveOneMoreCard() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printCardsWithSum(List<Player> players, Dealer dealer) {
        System.out.println(NEW_LINE);
        printParticipantCards("딜러", dealer.getCards());
        System.out.println(" - 결과: " + dealer.calculateSumOfRank());
        for (Player player : players) {
            printParticipantCards(player.getName(), player.getCards());
            System.out.println(" - 결과: " + player.calculateSumOfRank());
        }
    }

    public static void printFinalResult(final List<Player> players, final Map<Result, Integer> dealerResult) {
        StringBuilder finalResult = new StringBuilder();
        for (Result result : dealerResult.keySet()) {
            if (dealerResult.get(result) != 0) {
                finalResult.append(dealerResult.get(result)).append(result.getValue());
            }
        }
        System.out.println(NEW_LINE + "## 최종 승패");
        System.out.println("딜러: " + finalResult);
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getResult().getValue());
        }
    }
}
