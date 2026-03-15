package domain.result;

public enum ResultInfo {
    WIN {
        @Override
        public int calculateProfit(int amount) {
            return amount;
        }
    },
    DRAW {
        @Override
        public int calculateProfit(int amount) {
            return 0;
        }
    },
    DEFEAT {
        @Override
        public int calculateProfit(int amount) {
            return -amount;
        }
    },
    BLACKJACK_WIN {
        @Override
        public int calculateProfit(int amount) {
            return (int) (amount * 1.5);
        }
    };

    public abstract int calculateProfit(int amount);
}
