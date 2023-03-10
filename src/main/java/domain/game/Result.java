package domain.game;

public enum Result {
    BLACKJACK {
        @Override
        public int calculateProfit(final int input) {
            return (int) (PROFIT_RATE * input);
        }
    },
    WIN {
        @Override
        public int calculateProfit(final int input) {
            return input;
        }
    },
    DRAW {
        @Override
        public int calculateProfit(final int input) {
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(final int input) {
            return -input;
        }
    };

    public static final double PROFIT_RATE = 1.5;

    public abstract int calculateProfit(final int input);
}
