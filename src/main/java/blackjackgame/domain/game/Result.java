package blackjackgame.domain.game;

public enum Result {
    WIN {
        @Override
        public int calculateProfit(int betAmount) {
            return betAmount;
        }
    },
    WIN_BLACKJACK {
        @Override
        public int calculateProfit(int betAmount) {
            return (int) (betAmount * 1.5);
        }
    },
    DRAW {
        @Override
        public int calculateProfit(int betAmount) {
            return 0;
        }
    },
    LOSE {
        @Override
        public int calculateProfit(int betAmount) {
            return -betAmount;
        }
    };

    public abstract int calculateProfit(int betAmount);
}
