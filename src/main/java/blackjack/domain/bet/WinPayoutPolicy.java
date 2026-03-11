package blackjack.domain.bet;

public class WinPayoutPolicy implements PayoutPolicy {

    private static final double WIN_PAYOUT_COEFFICIENT = 2.0D;

    @Override
    public int payout(final int amount) {
        return (int) (amount * WIN_PAYOUT_COEFFICIENT);
    }
}
