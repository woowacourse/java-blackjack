package model.judgement;

public enum ResultStatus {

    WIN("승") {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toProfit();
        }
    },
    LOSE("패") {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toNegativeProfit();
        }
    },
    DRAW("무") {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return Profit.ZERO;
        }
    },
    BLACKJACK("승") {
        @Override
        public Profit calculateProfit(BetAmount betAmount) {
            return betAmount.toBlackjackProfit();
        }
    };

    private final String name;

    ResultStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Profit calculateProfit(BetAmount betAmount);
}
