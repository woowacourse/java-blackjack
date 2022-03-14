package blackjack.domain.player;

import blackjack.domain.result.Result;

public class Bet {

    private static final int MIN = 0;

    private int amount;

    public Bet(final int amount) {
        checkBetRightRange(amount);
        this.amount = amount;
    }

    private void checkBetRightRange(int bet) {
        if (bet <= MIN) {
            throw new IllegalArgumentException("[ERROR] 베팅은 1원부터 가능합니다.");
        }
    }

    public void calculateBenefit(final Result result) {
        amount = result.calculateBet(amount);
    }

    public int getAmount() {
        return amount;
    }
}
