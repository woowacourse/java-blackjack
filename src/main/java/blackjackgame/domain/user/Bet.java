package blackjackgame.domain.user;

import blackjackgame.domain.game.Result;

public class Bet {
    private static final int PROFIT_INITIAL_VALUE = 0;

    private final int amount;
    private int profit = PROFIT_INITIAL_VALUE;

    public Bet(int amount) {
        validateBetAmount(amount);
        this.amount = amount;
    }

    private void validateBetAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 1원 이상입니다.");
        }
    }

    public void applyResult(Result result) {
        profit = result.calculateProfit(amount);
    }

    public int profit() {
        return this.profit;
    }
}
