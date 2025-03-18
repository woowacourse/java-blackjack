package client;

import static card.CardNumberType.*;
import static card.CardShapeType.*;

import card.Card;
import card.CardNumberType;
import card.CardShapeType;
import hand.Hand;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutputFormatter {

    private static final String NAME_SEPARATOR = ", ";
    private static final String CARD_SEPARATOR = ", ";

    private static final Map<CardShapeType, String> CARD_TYPE_FORMATTER = Map.of(
            SPACE, "스페이드",
            HEART, "하트",
            CLOVER, "클로버",
            DIAMOND, "다이아몬드"
    );

    private static final Map<CardNumberType, String> CARD_NUMBER_TYPE_FORMATTER = new HashMap<>();

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

    public String formatCard(Card card) {
        return CARD_NUMBER_TYPE_FORMATTER.get(card.cardNumberType()) + CARD_TYPE_FORMATTER.get(card.cardShapeType());
    }
}
