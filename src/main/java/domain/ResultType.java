package domain;

public enum ResultType {
    WIN,
    BLACKJACK,
    LOSE;

    private static final double BLACKAJACK_PROFIT_RATE = 1.5;

    public Money calculateBetting(Money money) {
        if (this == ResultType.BLACKJACK) {
            return money.multiply(BLACKAJACK_PROFIT_RATE);
        }
        if (this == ResultType.LOSE) {
            return money.negative();
        }
        return money;
    }

}
