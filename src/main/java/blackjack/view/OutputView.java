package blackjack.view;

import blackjack.domain.card.Card;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

import static blackjack.domain.gamer.Dealer.HIT_UPPER_BOUND;

public class OutputView {
    static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";
    private static final String DEAL_ANNOUNCE_FORMAT = String.format("%s와 %%s에게 2장을 나누었습니다.", DEALER_NAME);
    private static final String DEALER_HIT_FORMAT = String.format("%s는 %d 이하라 한장의 카드를 더 받았습니다.", DEALER_NAME, HIT_UPPER_BOUND);
    private static final String CARD_FORMAT = "%s 카드 : %s";
    private static final String RESULT_SCORES_FORMAT = " - 결과: %d";
    private static final String RESULT_FINAL_PROFIT = "## 최종 수익";
    private static final String PROFIT_FORMAT = "%s: %s";

    public static void printDealAnnounce(List<String> playersName) {
        String nameFormat = String.join(DELIMITER, playersName);
        System.out.printf(LINE_SEPARATOR + DEAL_ANNOUNCE_FORMAT + LINE_SEPARATOR, nameFormat);
    }

    public static void printDealerDealCard(Card card) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, DEALER_NAME, formatCard(card));
    }

    public static void printDealCards(String name, List<Card> cards) {
        System.out.printf(CARD_FORMAT + LINE_SEPARATOR, name, formatCards(cards));
    }

    private static String formatCards(List<Card> cards) {
        return cards.stream()
                .map(OutputView::formatCard)
                .collect(Collectors.joining(DELIMITER));
    }

    private static String formatCard(Card card) {
        return RankName.convert(card.rank()) + SuitName.convert(card.suit());
    }

    public static void printDealerHitAnnounce() {
        System.out.println(LINE_SEPARATOR + DEALER_HIT_FORMAT + LINE_SEPARATOR);
    }

    public static void printDealerCards(List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, DEALER_NAME, formatCards(cards), totalScore);
    }

    public static void printPlayerCards(String name, List<Card> cards, int totalScore) {
        System.out.printf(CARD_FORMAT + RESULT_SCORES_FORMAT + LINE_SEPARATOR, name, formatCards(cards), totalScore);
    }

    public static void printFinalProfitAnnounce() {
        System.out.println(LINE_SEPARATOR + RESULT_FINAL_PROFIT);
    }

    public static void printDealerProfit(Double profit) {
        NumberFormat formatter = new DecimalFormat("#.###");
        System.out.printf((PROFIT_FORMAT) + "%n", DEALER_NAME, formatter.format(profit));
    }

    public static void printPlayerProfit(String name, Double profit) {
        NumberFormat formatter = new DecimalFormat("#.###");
        System.out.printf((PROFIT_FORMAT) + "%n", name, formatter.format(profit));
    }
}
