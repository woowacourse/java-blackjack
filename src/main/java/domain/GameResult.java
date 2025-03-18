package domain;

import domain.participant.Money;

public enum GameResult {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final double returnRate;

    GameResult(double returnRate) {
        this.returnRate = returnRate;
    }

    public GameResult getReverse() {
        return switch (this) {
            case LOSE -> WIN;
            case WIN, BLACKJACK -> LOSE;
            case DRAW -> DRAW;
        };
    }

    public Money applyReturnRate(Money money) {
        return money.times(returnRate);
    }
}
