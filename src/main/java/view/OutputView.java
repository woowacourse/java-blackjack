package view;

import domain.card.Card;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import utils.CardConvertor;

public class OutputView {
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    private OutputView() {
    }

    public static void printInitCardsResult(final Map<String, List<Card>> cardsWithName) {
        printInitCardsIntro(cardsWithName.keySet());
        printCardsWithName(cardsWithName);
    }

    private static void printInitCardsIntro(final Set<String> names) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(String.join(", ", names))
                .append("에게 2장을 나누었습니다 *^^*");
        print(stringBuilder.toString());
    }

    public static void printCardsWithName(Map<String, List<Card>> cardsWithName) {
        for (final Entry<String, List<Card>> entry : cardsWithName.entrySet()) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder
                    .append(entry.getKey())
                    .append("카드: ")
                    .append(convertToString(entry.getValue()));
            print(stringBuilder.toString());
        }
    }

    private static String convertToString(final List<Card> cards) {
        return String.join(", ", CardConvertor.convertToString(cards));
    }

    private static void print(final String content) {
        System.out.println(content);
    }

    public static void printDealerHit() {
        print(DEALER_HIT_MESSAGE);
    }
}
