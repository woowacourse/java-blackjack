package domain;

public enum ResultType {
    WIN,
    BLACKJACK,
    LOSE;

    private static final double BLACKAJACK_PROFIT_RATE = 1.5;

    public Money calculateBetting(Money bettingMoney) {
        if (this == ResultType.BLACKJACK) {
            return bettingMoney.multiply(BLACKAJACK_PROFIT_RATE);
        }
        if (this == ResultType.LOSE) {
            return bettingMoney.negative();
        }
        return bettingMoney.stay();
    }

}
