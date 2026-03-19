package domain;

public enum GameResult {
    BLACKJACK_WIN {
        @Override
        public int profit(Money money) {
            return (int) (money.getBetAmount() * MONEY_MULTIPLY_PERCENT);
        }
    },
    WIN {
        @Override
        public int profit(Money money) {
            return money.getBetAmount();
        }
    },
    LOSE {
        @Override
        public int profit(Money money) {
            return -money.getBetAmount();
        }
    },
    DRAW {
        @Override
        public int profit(Money money) {
            return 0;
        }
    };

    private static final double MONEY_MULTIPLY_PERCENT = 1.5;

    public abstract int profit(Money money);
}
