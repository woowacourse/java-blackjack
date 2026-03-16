package domain;

public enum GameResult {
    BLACKJACK_WIN {
        @Override
        public int profit(Money money) {
            return money.multiply();
        }
    },
    WIN {
        @Override
        public int profit(Money money) {
            return money.add();
        }
    },
    LOSE {
        @Override
        public int profit(Money money) {
            return money.subtract();
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
