package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.game.Result;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String JOIN_DELIMITER = ", ";

    public static void printInitialHands(Dealer dealer, List<Player> players) {
        System.out.println(initialHandsMessageBuilder(dealer, players));
    }

    public static void printTotalHand(Player player) {
        System.out.println(allCardInHand(player));
    }

    public static void printHandsWithScore(Dealer dealer, List<Player> players) {
        System.out.println(handWithScoreMessageBuilder(dealer, players));
    }

    public static void printResult(Result result, Dealer dealer) {
        Map<Player, Integer> playerResults = result.getResults();
        int dealerEarning = result.calculateDealerEarning();

        System.out.println("## 최종 수익");
        System.out.println(dealerEarningMessageBuilder(dealer, dealerEarning));
        System.out.print(playerResultsMessageBuilder(playerResults));
    }

    public static void printPlayerBlackjack(String playerName) {
        System.out.printf("%s는 블랙잭 입니다.%n", playerName);
    }

    public static void printBust(String playerName) {
        System.out.printf("%s는 버스트 입니다.%n", playerName);
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.printf("%n%s는 16이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    private static StringBuilder initialHandsMessageBuilder(Dealer dealer, List<Player> players) {
        StringBuilder builder = new StringBuilder();

        builder.append(dealOutMessage(dealer, players))
                .append(dealerSingleCard(dealer));
        for (Player player : players) {
            builder.append(allCardInHand(player))
                    .append(LINE_SEPARATOR);
        }
        return builder;
    }

    private static String dealOutMessage(Dealer dealer, List<Player> players) {
        String commaSeparatePlayerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));

        return String.format("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), commaSeparatePlayerNames);
    }

    private static String dealerSingleCard(Dealer dealer) {
        List<Card> cards = dealer.getHand().getCards();
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }

        return String.format("%s: %s%n", dealer.getName(), extractCardName(cards.get(0)));
    }

    private static StringBuilder handWithScoreMessageBuilder(Dealer dealer, List<Player> players) {
        StringBuilder builder = new StringBuilder();

        builder.append(LINE_SEPARATOR)
                .append(getHandWithScoreMessage(dealer))
                .append(LINE_SEPARATOR);
        for (Player player : players) {
            builder.append(getHandWithScoreMessage(player))
                    .append(LINE_SEPARATOR);
        }
        return builder;
    }

    private static String getHandWithScoreMessage(Participant player) {
        return allCardInHand(player) + resultScore(player);
    }

    private static String allCardInHand(Participant player) {
        List<Card> cards = player.getHand().getCards();
        String hand = cards.stream()
                .map(OutputView::extractCardName)
                .collect(Collectors.joining(JOIN_DELIMITER));

        return String.format("%s: %s", player.getName(), hand);
    }

    private static String resultScore(Participant player) {
        int score = player.calculate();
        return String.format(" - 결과: %d", score);
    }

    private static String extractCardName(Card card) {
        return card.getNumber().getName() + card.getSymbol().getName();
    }

    private static StringBuilder dealerEarningMessageBuilder(Dealer dealer, int dealerEarning) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s: %d", dealer.getName(), dealerEarning));
        return builder;
    }

    private static StringBuilder playerResultsMessageBuilder(Map<Player, Integer> playerResults) {
        StringBuilder builder = new StringBuilder();

        playerResults.forEach(
                ((player, earning) -> builder.append(String.format("%s: %d%n", player.getName(), earning))));
        return builder;
    }
}
