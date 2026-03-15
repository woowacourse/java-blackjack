package domain;

public enum GameResult {
    BLACKJACK_WIN {
        @Override
        public int profit(Money money) {
            return money.earnOnePointFiveTimes();
        }
    },
    WIN {
        @Override
        public int profit(Money money) {
            return money.earn();
        }
    },
    LOSE {
        @Override
        public int profit(Money money) {
            return money.lose();
        }
    },
    DRAW {
        @Override
        public int profit(Money money) {
            return money.getBack();
        }
    };

    public abstract int profit(Money money);
}
