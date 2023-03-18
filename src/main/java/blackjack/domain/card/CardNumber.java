package blackjack.domain.card;

import java.util.Arrays;

public enum CardNumber {
    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10);

    private final String number;
    private final int value;

    CardNumber(final String number, final int value) {
        this.number = number;
        this.value = value;
    }

    public static CardNumber of(String inputNumber) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.number.equals(inputNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 카드넘버입니다."));
    }

    public int getValue() {
        return this.value;
    }

    public String getNumber() {
        return this.number;
    }
}
