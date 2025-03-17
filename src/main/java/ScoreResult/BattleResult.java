package ScoreResult;

import bank.Money;

public enum BattleResult {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACK_JACK(1.5)
    ;

    private final double severalTimes;

    BattleResult(final double severalTimes) {
        this.severalTimes = severalTimes;
    }

    public BattleResult reverse() {
        if (this == WIN) return LOSE;
        if (this == LOSE) return WIN;
        return DRAW;
    }

    public Money calculateProfit(final Money amount) {
        return Money.multiply(amount, severalTimes);
    }

}
