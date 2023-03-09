package blackjack.domain;

public enum ResultType {
    BLACKJACK_WIN("블랙잭 승리") {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_LOSE;
        }
    },
    WIN("승") {
        @Override
        public ResultType getOppositeResult() {
            return LOSE;
        }
    },
    TIE("무") {
        @Override
        public ResultType getOppositeResult() {
            return TIE;
        }
    },
    LOSE("패") {
        @Override
        public ResultType getOppositeResult() {
            return WIN;
        }
    },
    BLACKJACK_LOSE("블랙잭 패배") {
        @Override
        public ResultType getOppositeResult() {
            return BLACKJACK_WIN;
        }
    };

    private final String name;

    ResultType(final String name) {
        this.name = name;
    }

    public abstract ResultType getOppositeResult();

    public String getName() {
        return name;
    }
}
