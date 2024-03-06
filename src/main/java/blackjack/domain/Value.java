package blackjack.domain;

public enum Value {

    NONE(0),
    ACE(1), TWO(2), THREE(3),
    FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9),
    TEN(10), JACK(10), QUEEN(10), KING(10);

    private final int score;

    Value(int score) {
        this.score = score;
    }

    public static Value findValue(int cardNumber) {
        if (cardNumber < 1 || cardNumber >= values().length) {
            throw new IllegalStateException("존재하지 않는 카드 번호입니다.");
        }
        return values()[cardNumber];
    }
}
