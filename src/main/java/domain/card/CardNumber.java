package domain.card;

import java.util.Arrays;

public enum CardNumber {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int number;
    private final String court;

    CardNumber(int number, String court) {
        this.number = number;
        this.court = court;
    }

    public static CardNumber matchCardNumber(String value) {
        return Arrays.stream(CardNumber.values())
                .filter(cardNumber -> cardNumber.court.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 카드 번호를 찾을 수 없습니다: " + value));
    }

    public int getValue() {
        return number;
    }

    public String getCourt() {
        return court;
    }
}
