package blackjack.domain.result;

import blackjack.domain.money.Money;

public enum GameResult {

    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final int multiplier;

    GameResult(int multiplier) {
        this.multiplier = multiplier;
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public Money profitOf(Money wager) {
        return wager.multiply(multiplier);
    }
}
