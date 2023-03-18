package view.message;

import domain.card.Denomination;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum NumberMessage {

    ACE(Denomination.ACE, "A"),
    TWO(Denomination.TWO, "2"),
    THREE(Denomination.THREE, "3"),
    FOUR(Denomination.FOUR, "4"),
    FIVE(Denomination.FIVE, "5"),
    SIX(Denomination.SIX, "6"),
    SEVEN(Denomination.SEVEN, "7"),
    EIGHT(Denomination.EIGHT, "8"),
    NINE(Denomination.NINE, "9"),
    TEN(Denomination.TEN, "10"),
    KING(Denomination.KING, "K"),
    QUEEN(Denomination.QUEEN, "Q"),
    JACK(Denomination.JACK, "J");

    private static final Map<Denomination, String> CACHE = Stream.of(NumberMessage.values())
            .collect(Collectors.toUnmodifiableMap(numberMessage -> numberMessage.number,
                    numberMessage -> numberMessage.message));

    private final Denomination number;
    private final String message;

    NumberMessage(final Denomination number, final String message) {
        this.number = number;
        this.message = message;
    }

    public static String findMessage(Denomination number) {
        return CACHE.get(number);
    }
}
