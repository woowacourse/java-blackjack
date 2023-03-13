package domain.bet;

import domain.result.Outcome;

public final class Bet {

    public static final double BLACKJACK_WIN_RATIO = 1.5;
    public static final double DRAW_RATIO = 0;
    public static final double LOSE_RATIO = -1.0;

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
            this.money = (int) (this.money * BLACKJACK_WIN_RATIO);
            return;
        }
        if (Outcome.DRAW == outcome) {
            this.money = (int) (this.money * DRAW_RATIO);
            return;
        }
        if (Outcome.LOSE == outcome) {
            this.money = (int) (this.money * LOSE_RATIO);
        }
    }

    public int getMoney() {
        return money;
    }
}
