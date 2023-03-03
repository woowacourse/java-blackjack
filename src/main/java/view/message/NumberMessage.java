package view.message;

import domain.card.CardNumber;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum NumberMessage {

    ACE(CardNumber.ACE, "A"),
    TWO(CardNumber.TWO, "2"),
    THREE(CardNumber.THREE, "3"),
    FOUR(CardNumber.FOUR, "4"),
    FIVE(CardNumber.FIVE, "5"),
    SIX(CardNumber.SIX, "6"),
    SEVEN(CardNumber.SEVEN, "7"),
    EIGHT(CardNumber.EIGHT, "8"),
    NINE(CardNumber.NINE, "9"),
    TEN(CardNumber.TEN, "10"),
    KING(CardNumber.KING, "K"),
    QUEEN(CardNumber.QUEEN, "Q"),
    JACK(CardNumber.JACK, "J");

    private static final Map<CardNumber, String> CACHE = Stream.of(NumberMessage.values())
            .collect(Collectors.toUnmodifiableMap(numberMessage -> numberMessage.number,
                    numberMessage -> numberMessage.message));

    private final CardNumber number;
    private final String message;

    NumberMessage(final CardNumber number, final String message) {
        this.number = number;
        this.message = message;
    }

    public static String findMessage(CardNumber number) {
        return CACHE.get(number);
    }
}
