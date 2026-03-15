package blackjack.domain.bet;

import blackjack.domain.participant.GameResult;

public final class Profit {

    private Profit() {

    }

    public static int calculate(final Bet bet, final GameResult gameResult) {
        return calculatePayout(bet, gameResult) - bet.getAmount();
    }

    private static int calculatePayout(final Bet bet, final GameResult gameResult) {
        return (int) (bet.getAmount() * gameResult.getPayoutCoefficient());
    }

}
