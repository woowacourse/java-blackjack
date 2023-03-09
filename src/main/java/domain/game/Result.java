package domain.game;

public enum Result {
    WIN {
        @Override
        public int calculateProfit(final int input) {
            return input;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(final int input) {
            return -input;
        }
    },
    DRAW {
        @Override
        public int calculateProfit(final int input) {
            return 0;
        }
    };

    public abstract int calculateProfit(final int input);
}
