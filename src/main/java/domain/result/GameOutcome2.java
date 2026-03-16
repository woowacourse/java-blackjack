package domain.result;

import domain.common.Money;

public enum GameOutcome2 {

    FIRST_BLACK_JACK_WIN(1.5),
    WIN(1),

    FIRST_BLACK_JACK_DRAW(0),
    DRAW(0),

    LOSE(-1),
    ;

    private final double odds;

    GameOutcome2(double odds) {
        this.odds = odds;
    }

    public long payout(Money money) {
        return Math.round(money.amount() * odds);
    }
}
