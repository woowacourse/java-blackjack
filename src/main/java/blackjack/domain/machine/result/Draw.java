package blackjack.domain.machine.result;

public final class Draw extends MatchCalculator {

    private static final int DRAW_RATE = 0;

    @Override
    double profitRate() {
        return DRAW_RATE;
    }
}
