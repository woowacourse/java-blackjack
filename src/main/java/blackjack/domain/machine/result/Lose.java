package blackjack.domain.machine.result;

public final class Lose extends MatchCalculator {

    public static final int LOSE_RATE = -1;

    @Override
    double profitRate() {
        return LOSE_RATE;
    }
}
