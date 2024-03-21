package blackjack.view;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static blackjack.domain.gamer.dealer.Dealer.HIT_UPPER_BOUND;
import static blackjack.domain.gamer.player.Player.DEAL_CARD_COUNT;

public class OutputView {
    static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DELIMITER = ", ";
    private static final String DEAL_ANNOUNCE_FORMAT = String.format("딜러와 %%s에게 %d장을 나누었습니다.", DEAL_CARD_COUNT);
    private static final String DEALER_HIT_FORMAT = String.format("딜러는 %d 이하라 한장의 카드를 더 받았습니다.", HIT_UPPER_BOUND);
    private static final String CARD_FORMAT = "%s 카드 : %s";
    private static final String RESULT_SCORES_FORMAT = " - 결과: %d";
    private static final String REVENUE_DECIMAL_FORMAT = "%s: %.1f";
    private static final String REVENUE_NO_DECIMAL_FORMAT = "%s: %.0f";
    private static final String REVENUE_ANNOUNCE = "## 최종 수익";

    public static void printDealAnnounce(List<String> names) {
        String nameFormat = String.join(DELIMITER, names);
        System.out.printf(DEAL_ANNOUNCE_FORMAT + LINE_SEPARATOR, nameFormat);
    }

    public static void printCard(String name, Card card) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCard(card));
    }

    public static void printCards(String name, List<Card> cards) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCards(cards));
    }

    private static String formatCard(Card card) {
        return CardScoreName.convert(card.score()) + CardSuitName.convert(card.suit());
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(DELIMITER));
    }


    public static void printNewLine() {
        System.out.println();
    }

    public static void printDealerHitAnnounce() {
        System.out.println(DEALER_HIT_FORMAT);
    }

    public static void printCardsWithScore(String name, List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, name, formatCards(cards), totalScore);
    }

    public static void printResult(String dealerName, double dealerRevenue, Map<String, Double> playerRevenues) {
        printRevenueAnnounce();
        printRevenue(dealerName, dealerRevenue);
        playerRevenues.forEach(OutputView::printRevenue);
    }

    private static void printRevenueAnnounce() {
        System.out.println(LINE_SEPARATOR + REVENUE_ANNOUNCE);
    }

    private static void printRevenue(String name, double revenue) {
        if (revenue == (long) revenue) {
            System.out.printf(REVENUE_NO_DECIMAL_FORMAT + LINE_SEPARATOR, name, revenue);
            return;
        }
        System.out.printf(REVENUE_DECIMAL_FORMAT + LINE_SEPARATOR, name, revenue);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
