package blackjack.model.results;

public enum Result {
    WIN_BY_BLACKJACK {
        @Override
        public double calculate(int money) {
            return (money * 1.5);
        }
    },
    WIN {
        @Override
        public double calculate(int money) {
            return money;
        }
    },
    PUSH {
        @Override
        public double calculate(int money) {
            return 0;
        }
    },
    LOSE {
        @Override
        public double calculate(int money) {
            return -money;
        }
    },
    LOSE_BY_BLACKJACK {
        @Override
        public double calculate(int money) {
            return -(money * 1.5);
        }
    };

    public abstract double calculate(int money);
}
