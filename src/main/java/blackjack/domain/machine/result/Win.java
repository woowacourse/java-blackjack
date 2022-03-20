package blackjack.domain.machine.result;

public final class Win extends MatchCalculator {

    public static final int WIN_RATE = 1;

    @Override
    double profitRate() {
        return WIN_RATE;
    }
}
