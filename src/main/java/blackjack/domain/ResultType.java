package blackjack.domain;

public enum ResultType {
    WIN("승") {
        @Override
        public int profit(int betAmount) {
            return betAmount;
        }
    },
    LOSS("패") {
        @Override
        public int profit(int betAmount) {
            return -betAmount;
        }
    },
    DRAW("무승부") {
        @Override
        public int profit(int betAmount) {
            return DRAW_MONEY;
        }
    },
    BLACKJACK("블랙잭") {
        @Override
        public int profit(int betAmount) {
            return (int) (betAmount * BLACK_JACK_ADDITIONAL_AMOUNT_MULTIPLIER);
        }
    };

    private static final int DRAW_MONEY = 0;
    private static final double BLACK_JACK_ADDITIONAL_AMOUNT_MULTIPLIER = 1.5;

    private final String result;

    ResultType(String result) {
        this.result = result;
    }

    abstract public int profit(int betAmount);
}