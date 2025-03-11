package blackjack.model;

public enum MatchResult {

    WIN("승") {
        @Override
        public MatchResult getReversed() {
            return LOSE;
        }
    },
    LOSE("패") {
        @Override
        public MatchResult getReversed() {
            return WIN;
        }
    },
    DRAW("무") {
        @Override
        public MatchResult getReversed() {
            return DRAW;
        }
    },
    ;

    private final String label;

    MatchResult(String label) {
        this.label = label;
    }

    public abstract MatchResult getReversed();

    public String getLabel() {
        return label;
    }
}
