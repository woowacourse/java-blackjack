package blackjack.domain;

public enum ResultType {
    BLACKJACK_WIN("블랙잭 승리", 1.5) {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_LOSE;
        }
    },
    WIN("승", 1) {
        @Override
        public ResultType getOppositeResult() {
            return LOSE;
        }
    },
    TIE("무", 0) {
        @Override
        public ResultType getOppositeResult() {
            return TIE;
        }
    },
    LOSE("패", -1) {
        @Override
        public ResultType getOppositeResult() {
            return WIN;
        }
    },
    BLACKJACK_LOSE("블랙잭 패배", -1) {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_WIN;
        }
    };

    private final String name;
    private final double playerProfit;

    ResultType(final String name, final double playerProfit) {
        this.name = name;
        this.playerProfit = playerProfit;
    }

    public abstract ResultType getOppositeResult();

    public String getName() {
        return name;
    }

    public double getPlayerProfit() {
        return playerProfit;
    }
}
