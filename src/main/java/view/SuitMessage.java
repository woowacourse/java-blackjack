package view;

import domain.card.Suit;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SuitMessage {
    SPADE(Suit.SPADE, "스페이드"),
    HEART(Suit.HEART, "하트"),
    DIAMOND(Suit.DIAMOND, "다이아"),
    CLOVER(Suit.CLOVER, "클로버");

    private final Suit suit;
    private final String message;

    SuitMessage(final Suit suit, final String message) {
        this.suit = suit;
        this.message = message;
    }

    private static final Map<Suit, String> suitMessage =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(view.SuitMessage::getSuit, view.SuitMessage::getMessage)));

    public static String getSuitMessage(Suit suit) {
        return suitMessage.get(suit);
    }
    Suit getSuit() {
        return suit;
    }

    String getMessage() {
        return message;
    }
}
