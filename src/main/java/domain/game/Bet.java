package domain.game;

import java.text.MessageFormat;

public final class Bet {

    public static final int MAX = 100_000_000;
    public static final double BONUS_RATE = 1.5;

    private final int bet;
    private int profit;

    private Bet(final int bet) {
        validatePrice(bet);
        this.bet = bet;
    }

    public static Bet of(final int bet) {
        return new Bet(bet);
    }

    private void validatePrice(final int bet) {
        if (bet > MAX) {
            throw new IllegalArgumentException(
                    MessageFormat.format(
                            "{0}초과의 베팅은 할 수 없습니다.",
                            MAX
                    )
            );
        }
    }

    public int applyBonus() {
        this.profit = (int) (bet * BONUS_RATE);
        return profit;
    }

    public int applyBust() {
        this.profit = -bet;
        return profit;
    }
}
