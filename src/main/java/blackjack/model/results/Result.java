package blackjack.model.results;

public enum Result {
    WIN_BY_BLACKJACK {
        @Override
        public int calculate(int money) {
            return (int) (money * 1.5);
        }
    },
    WIN {
        @Override
        public int calculate(int money) {
            return money;
        }
    },
    PUSH {
        @Override
        public int calculate(int money) {
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculate(int money) {
            return -money;
        }
    },
    LOSE_BY_BLACKJACK {
        @Override
        public int calculate(int money) {
            return (int) -(money * 1.5);
        }
    };

    public abstract int calculate(int money);
}
