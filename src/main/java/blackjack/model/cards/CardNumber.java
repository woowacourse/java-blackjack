package blackjack.model.cards;

import java.util.Arrays;

public enum CardNumber {
    ACE(0, 1),
    TWO(1, 2),
    THREE(2, 3),
    FOUR(3, 4),
    FIVE(4, 5),
    SIX(5, 6),
    SEVEN(6, 7),
    EIGHT(7, 8),
    NINE(8, 9),
    TEN(9, 10),
    JACK(10, 10),
    QUEEN(11, 10),
    KING(12, 10);

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
