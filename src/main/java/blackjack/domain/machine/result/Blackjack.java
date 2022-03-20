package blackjack.domain.machine.result;

public final class Blackjack extends MatchCalculator {

    private static final double BLACKJACK_RATE = 1.5;

    @Override
    public double profitRate() {
        return BLACKJACK_RATE;
    }
}
