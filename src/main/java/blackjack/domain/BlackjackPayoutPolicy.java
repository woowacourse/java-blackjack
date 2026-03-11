package blackjack.domain;

public class BlackjackPayoutPolicy implements PayoutPolicy {

    private static final double BLACKJACK_PAYOUT_COEFFICIENT = 1.5D;

    @Override
    public int payout(final int amount) {
        return (int) (amount * BLACKJACK_PAYOUT_COEFFICIENT);
    }
}
