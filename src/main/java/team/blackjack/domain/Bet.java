package team.blackjack.domain;

public final class Bet {
    private static final double BLACKJACK_PAYOUT_RATIO = 1.5;
    private static final double WIN_PAYOUT_RATIO = 1.0;
    private static final double DRAW_PAYOUT_RATIO = 0.0;
    private static final double LOSE_PAYOUT_RATIO = -1.0;

    private final int stake;

    private Bet(int stake) {
        this.stake = stake;
    }

    public static Bet from(int stake) {
        return new Bet(stake);
    }

    public int calculatePayout(Result result) {
        return (int) (stake * payoutRatio(result));
    }

    private double payoutRatio(Result result) {
        if (result == Result.BLACKJACK) {
            return BLACKJACK_PAYOUT_RATIO;
        }
        if (result == Result.WIN) {
            return WIN_PAYOUT_RATIO;
        }
        if (result == Result.LOSE) {
            return LOSE_PAYOUT_RATIO;
        }
        return DRAW_PAYOUT_RATIO;
    }
}
