package blackjack.model;

import java.util.Arrays;

public enum CardNumber {
    ACE(1, 1),
    TWO(2, 2),
    THREE(3, 3),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 7),
    EIGHT(8, 8),
    NINE(9, 9),
    TEN(10, 10),
    JACK(11, 10),
    QUEEN(12, 10),
    KING(13, 10);

    private final int order;
    private final int score;

    CardNumber(int order, int score) {
        this.order = order;
        this.score = score;
    }

    public static CardNumber generate(int number) {
        return Arrays.stream(values())
                .filter(cardNumber -> cardNumber.order == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("카드숫자를 고르는 숫자가 올바르지 않습니다."));
    }

    public boolean isAce() {
        return ACE.equals(this);
    }

    public int getScore() {
        return score;
    }
}
