package blackjack.domain.player;

import blackjack.domain.Score;

public class BettingAmount {

    private final int amount;

    public BettingAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅금액은 0보다 커야 합니다.");
        }
        this.amount = amount;
    }

    public double getDividend(Score score) {
        return amount * score.getDividendRate();
    }
}
