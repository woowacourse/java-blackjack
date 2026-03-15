package blackjack.domain.result;

import blackjack.domain.money.Money;

public enum GameResult {

    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double multiplier;

    GameResult(double multiplier) {
        this.multiplier = multiplier;
    }

    public Money profitOf(Money wager) {
        return wager.multiply(multiplier);
    }
}
