package blackjack.view;

import blackjack.Card;
import blackjack.Cards;
import blackjack.Denomination;
import blackjack.Receipt;
import blackjack.Suit;
import blackjack.user.Participant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String INITIAL_DISTRIBUTION_END_MESSAGE = "%s와 %s에게 2장을 나눠주었습니다.\n";
    private static final String BASIC_DELIMITER = ", ";
    private static final Map<Suit, String> suitToString = new HashMap<>();
    private static final Map<Denomination, String> denominationToString = new HashMap<>();
    private static final String DEALER_CARDS = "%s: %s\n";
    private static final String PLAYER_CARDS = "%s카드: %s";
    private static final String DEALER_ADDTIONAL_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String RESULT_SCORE = " - 결과: %d\n";
    private static final String RESULT_MESSAGE = "\n## 최종 수익";
    private static final String PARTICIPANT_YIELD_MESSAGE = "%s: %d\n";
    private static final String DEALER = "딜러";

    static {
        suitToString.put(Suit.CLOVER, "클로버");
        suitToString.put(Suit.DIAMOND, "다이아몬드");
        suitToString.put(Suit.SPADE, "스페이드");
        suitToString.put(Suit.HEART, "하트");

        denominationToString.put(Denomination.ACE, "A");
        denominationToString.put(Denomination.TWO, "2");
        denominationToString.put(Denomination.THREE, "3");
        denominationToString.put(Denomination.FOUR, "4");
        denominationToString.put(Denomination.FIVE, "5");
        denominationToString.put(Denomination.SIX, "6");
        denominationToString.put(Denomination.SEVEN, "7");
        denominationToString.put(Denomination.EIGHT, "8");
        denominationToString.put(Denomination.NINE, "9");
        denominationToString.put(Denomination.TEN, "10");
        denominationToString.put(Denomination.J, "J");
        denominationToString.put(Denomination.Q, "Q");
        denominationToString.put(Denomination.K, "K");
    }

    public static void printInitialDistributionEndMessage(String dealerName, List<String> names) {
        String nameFormat = String.join(BASIC_DELIMITER, names);
        System.out.printf(INITIAL_DISTRIBUTION_END_MESSAGE, dealerName, nameFormat);
    }

    public static void printDealerCards(String name, Cards cards) {
        System.out.printf(DEALER_CARDS, name, makeCardFormat(cards));
    }

    public static void printCards(String name, Cards cards, boolean newLine) {
        System.out.printf(PLAYER_CARDS, name, makeCardFormat(cards));
        if (newLine) {
            System.out.println();
        }
    }

    public static void printScore(int score) {
        System.out.printf(RESULT_SCORE, score);
    }

    public static void printDealerAdditionalCardMessage() {
        System.out.println(DEALER_ADDTIONAL_MESSAGE);
    }

    private static String makeCardFormat(Cards cards) {
        List<String> stringOfCards = cards.getCards()
                .stream()
                .map(OutputView::cardToString)
                .collect(Collectors.toList());
        return String.join(BASIC_DELIMITER, stringOfCards);
    }

    private static String cardToString(Card card) {
        return denominationToString.get(card.getDenomination()) + suitToString.get(card.getSuit());
    }

    public static void result(Map<Participant, Receipt> calculateYield) {
        System.out.println(RESULT_MESSAGE);
        dealerResult(calculateYield);
        for (Map.Entry<Participant, Receipt> entry : calculateYield.entrySet()) {
            System.out.printf(PARTICIPANT_YIELD_MESSAGE, entry.getKey().getName(), entry.getValue().money());
        }
    }

    private static void dealerResult(Map<Participant, Receipt> calculateYield) {
        int yieldSum = 0;
        for (Receipt receipt : calculateYield.values()) {
            yieldSum += receipt.money();
        }
        System.out.printf(PARTICIPANT_YIELD_MESSAGE, DEALER, -yieldSum);
    }
}
