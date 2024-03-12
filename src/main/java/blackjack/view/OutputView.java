package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.Result;
import blackjack.domain.ResultStatus;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

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
        Map<Player, ResultStatus> playerResults = result.getResults();
        Map<ResultStatus, Long> dealerResult = result.calculateDealerResult();

        System.out.println("## 최종 승패");
        System.out.println(dealerResultMessageBuilder(dealer, dealerResult));
        System.out.print(playerResultsMessageBuilder(playerResults));
    }

    public static void printBust() {
        System.out.println("버스트 되었습니다.");
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
                .collect(Collectors.joining(", "));

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

    private static String getHandWithScoreMessage(Player player) {
        return allCardInHand(player) + resultScore(player);
    }

    private static String allCardInHand(Player player) {
        List<Card> cards = player.getHand().getCards();
        String hand = cards.stream()
                .map(OutputView::extractCardName)
                .collect(Collectors.joining(", "));

        return String.format("%s: %s", player.getName(), hand);
    }

    private static String resultScore(Player player) {
        int score = player.calculate();
        return String.format(" - 결과: %d", score);
    }

    private static String extractCardName(Card card) {
        return card.getNumber().getName() + card.getSymbol().getName();
    }

    private static StringBuilder dealerResultMessageBuilder(Dealer dealer, Map<ResultStatus, Long> dealerResult) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s: ", dealer.getName()));
        dealerResult.forEach((key, value) ->
                builder.append(String.format(value + key.getName() + " ")));
        return builder;
    }

    private static StringBuilder playerResultsMessageBuilder(Map<Player, ResultStatus> playerResults) {
        StringBuilder builder = new StringBuilder();

        playerResults.forEach(
                ((player, status) -> builder.append(String.format("%s: %s%n", player.getName(), status.getName()))));
        return builder;
    }
}
