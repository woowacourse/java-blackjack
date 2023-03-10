package domain.game;

public enum Result {
    BLACKJACK {
        @Override
        public int calculateProfit(final int input) {
            return (int) (1.5 * input);
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

    public abstract int calculateProfit(final int input);
}
