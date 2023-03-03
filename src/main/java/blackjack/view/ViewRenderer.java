package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.CardNumber;
import blackjack.domain.CardShape;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewRenderer {

    private static final Map<CardShape, String> CARD_SHAPE_STRING_MAPPER;
    private static final Map<CardNumber, String> CARD_NUMBER_STRING_MAPPER;

    static {
        CARD_SHAPE_STRING_MAPPER = Map.of(
                CardShape.SPADE, "스페이드",
                CardShape.CLOVER, "클로버",
                CardShape.DIAMOND, "다이아몬드",
                CardShape.HEART, "하트"
        );

        CARD_NUMBER_STRING_MAPPER = new HashMap<>();
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.ACE, "A");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TWO, "2");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.THREE, "3");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FOUR, "4");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.FIVE, "5");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SIX, "6");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.SEVEN, "7");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.EIGHT, "8");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.NINE, "9");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.TEN, "10");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.JACK, "J");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.QUEEN, "Q");
        CARD_NUMBER_STRING_MAPPER.put(CardNumber.KING, "K");
    }

    public static Map<String, List<String>> renderStatus(Map<String, List<Card>> status) {
        Map<String, List<String>> renderedStatus = new HashMap<>();

        for (String name : status.keySet()) {
            renderedStatus.put(name, renderCardsToString(status.get(name)));
        }
        return renderedStatus;
    }

    private static List<String> renderCardsToString(List<Card> cards) {
        return cards.stream()
                .map(card -> CARD_NUMBER_STRING_MAPPER.get(card.getNumber())
                        + CARD_SHAPE_STRING_MAPPER.get(card.getShape()))
                .collect(Collectors.toUnmodifiableList());
    }

}
