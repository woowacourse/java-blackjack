package domain.card;

public class BlackJackRule {

    private static final int BURST_UPPER_BOUND = 21;

    private BlackJackRule() {
    }

    public static boolean isBurstBy(int value) {
        return value > BURST_UPPER_BOUND;
    }
}
