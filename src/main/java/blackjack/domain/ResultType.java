package blackjack.domain;

public enum ResultType {
    WIN() {
        @Override
        public int profit(int betAmount) {
            return betAmount;
        }
    },
    LOSS() {
        @Override
        public int profit(int betAmount) {
            return -betAmount;
        }
    },
    DRAW() {
        @Override
        public int profit(int betAmount) {
            return DRAW_MONEY;
        }
    },
    BLACKJACK() {
        @Override
        public int profit(int betAmount) {
            return (int) (betAmount * BLACK_JACK_ADDITIONAL_AMOUNT_MULTIPLIER);
        }
    };

    private static final int DRAW_MONEY = 0;
    private static final double BLACK_JACK_ADDITIONAL_AMOUNT_MULTIPLIER = 1.5;

    abstract public int profit(int betAmount);
}