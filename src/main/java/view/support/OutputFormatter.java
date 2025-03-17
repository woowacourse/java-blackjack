package view.support;

import static domain.CardNumberType.ACE;
import static domain.CardNumberType.EIGHT;
import static domain.CardNumberType.FIVE;
import static domain.CardNumberType.FOUR;
import static domain.CardNumberType.JACK;
import static domain.CardNumberType.KING;
import static domain.CardNumberType.NINE;
import static domain.CardNumberType.QUEEN;
import static domain.CardNumberType.SEVEN;
import static domain.CardNumberType.SIX;
import static domain.CardNumberType.TEN;
import static domain.CardNumberType.THREE;
import static domain.CardNumberType.TWO;
import static domain.CardType.CLOVER;
import static domain.CardType.DIAMOND;
import static domain.CardType.HEART;
import static domain.CardType.SPACE;

import domain.Card;
import domain.CardNumberType;
import domain.CardType;
import domain.Hand;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class OutputFormatter {

    private static final String NAME_SEPARATOR = ", ";
    private static final String CARD_SEPARATOR = ", ";

    private static final Map<CardType, String> CARD_TYPE_FORMATTER = new EnumMap<>(Map.of(
            SPACE, "스페이드",
            HEART, "하트",
            CLOVER, "클로버",
            DIAMOND, "다이아몬드"
    ));

    private static final Map<CardNumberType, String> CARD_NUMBER_TYPE_FORMATTER = new EnumMap<>(CardNumberType.class);

    static {
        CARD_NUMBER_TYPE_FORMATTER.put(ACE, "A");
        CARD_NUMBER_TYPE_FORMATTER.put(TWO, "2");
        CARD_NUMBER_TYPE_FORMATTER.put(THREE, "3");
        CARD_NUMBER_TYPE_FORMATTER.put(FOUR, "4");
        CARD_NUMBER_TYPE_FORMATTER.put(FIVE, "5");
        CARD_NUMBER_TYPE_FORMATTER.put(SIX, "6");
        CARD_NUMBER_TYPE_FORMATTER.put(SEVEN, "7");
        CARD_NUMBER_TYPE_FORMATTER.put(EIGHT, "8");
        CARD_NUMBER_TYPE_FORMATTER.put(NINE, "9");
        CARD_NUMBER_TYPE_FORMATTER.put(TEN, "10");
        CARD_NUMBER_TYPE_FORMATTER.put(JACK, "J");
        CARD_NUMBER_TYPE_FORMATTER.put(QUEEN, "Q");
        CARD_NUMBER_TYPE_FORMATTER.put(KING, "K");
    }

    public String formatPlayerNames(List<String> names) {
        return String.join(NAME_SEPARATOR, names).trim();
    }

    public String formatCards(Hand hand) {
        List<String> formattedCards = hand.getCards().stream()
                .map(this::formatCard)
                .toList();
        return String.join(CARD_SEPARATOR, formattedCards);
    }

    public String formatCards(List<Card> cards) {
        List<String> formattedCards = cards.stream()
                .map(this::formatCard)
                .toList();
        return String.join(CARD_SEPARATOR, formattedCards);
    }

    public String formatCard(Card card) {
        return CARD_NUMBER_TYPE_FORMATTER.get(card.cardNumberType()) + CARD_TYPE_FORMATTER.get(card.cardType());
    }
}
