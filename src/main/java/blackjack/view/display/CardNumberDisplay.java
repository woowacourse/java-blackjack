package blackjack.view.display;

import blackjack.card.Number;
import java.util.Arrays;

public enum CardNumberDisplay {

    ACE(Number.ACE, "A"),
    TWO(Number.TWO, "2"),
    THREE(Number.THREE, "3"),
    FOUR(Number.FOUR, "4"),
    FIVE(Number.FIVE, "5"),
    SIX(Number.SIX, "6"),
    SEVEN(Number.SEVEN, "7"),
    EIGHT(Number.EIGHT, "8"),
    NINE(Number.NINE, "9"),
    TEN(Number.TEN, "10"),
    JACK(Number.JACK, "J"),
    QUEEN(Number.QUEEN, "Q"),
    KING(Number.KING, "K");

    private final Number number;
    private final String notation;

    CardNumberDisplay(Number number, String notation) {
        this.number = number;
        this.notation = notation;
    }

    public static CardNumberDisplay getDisplayByNumber(Number number) {
        return Arrays.stream(CardNumberDisplay.values())
                .filter(displayNumber -> displayNumber.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 수입니다."));
    }

    public String getNotation() {
        return notation;
    }
}
