package model.judgement;

public enum ResultStatus {

    WIN {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toProfit();
        }
    },
    LOSE {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toNegativeProfit();
        }
    },
    DRAW {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return Profit.ZERO;
        }
    },
    BLACKJACK {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toBlackjackProfit();
        }
    };

    public abstract Profit calculateProfit(BetAmount betAmount);
}
