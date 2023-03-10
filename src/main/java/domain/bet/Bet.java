package domain.bet;

import domain.result.Outcome;

public final class Bet {

    private int money;

    public Bet(final int money) {
        validate(money);
        this.money = money;
    }

    private void validate(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("[ERROR]: 배팅금액은 0원 초과여야 합니다.");
        }
    }

    public void calculateBetByOutcome(final Outcome outcome, final boolean isBlackJack) {
        if (Outcome.WIN == outcome && isBlackJack) {
            this.money = (int) (this.money * 1.5);
            return;
        }
        if (Outcome.DRAW == outcome) {
            this.money = 0;
            return;
        }
        if (Outcome.LOSE == outcome) {
            this.money = this.money * (-1);
        }
    }

    public int getMoney() {
        return money;
    }
}
