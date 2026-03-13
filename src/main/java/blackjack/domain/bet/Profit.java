package blackjack.domain.bet;

import blackjack.domain.participant.GameResult;

public final class Profit {

    private static final double WIN_PAYOUT_COEFFICIENT = 2.0D;
    private static final double BLACKJACK_PAYOUT_COEFFICIENT = 1.5D;
    private static final double DRAW_PAYOUT_COEFFICIENT = 1.0D;
    private static final double LOSE_PAYOUT_COEFFICIENT = 0D;

    private Profit() {

    }

    public static int calculate(final Bet bet, final GameResult gameResult) {
        return calculatePayout(bet, gameResult) - bet.getAmount();
    }

    private static int calculatePayout(final Bet bet, final GameResult gameResult) {
        final int betAmount = bet.getAmount();
        if (gameResult == GameResult.WIN) {
            return (int) (betAmount * WIN_PAYOUT_COEFFICIENT);
        }
        if (gameResult == GameResult.BLACKJACK) {
            return (int) (betAmount * BLACKJACK_PAYOUT_COEFFICIENT);
        }
        if (gameResult == GameResult.DRAW) {
            return (int) (betAmount * DRAW_PAYOUT_COEFFICIENT);
        }
        return (int) (betAmount * LOSE_PAYOUT_COEFFICIENT);
    }

}
