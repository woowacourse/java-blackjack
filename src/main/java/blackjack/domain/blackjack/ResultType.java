package blackjack.domain.blackjack;

public enum ResultType {
    BLACKJACK_WIN(1.5) {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_LOSE;
        }
    },
    WIN(1) {
        @Override
        public ResultType getOppositeResult() {
            return LOSE;
        }
    },
    TIE(0) {
        @Override
        public ResultType getOppositeResult() {
            return TIE;
        }
    },
    LOSE(-1) {
        @Override
        public ResultType getOppositeResult() {
            return WIN;
        }
    },
    BLACKJACK_LOSE(-1) {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_WIN;
        }
    };

    private final double playerProfit;

    ResultType(final double playerProfit) {
        this.playerProfit = playerProfit;
    }

    public abstract ResultType getOppositeResult();

    public double getPlayerProfit() {
        return playerProfit;
    }
}
