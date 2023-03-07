package domain;

public enum GameOutcome {
    WIN("승") {
        @Override
        public GameOutcome oppositeOutcome() {
            return LOSE;
        }
    },
    DRAW("무") {
        @Override
        public GameOutcome oppositeOutcome() {
            return DRAW;
        }
    },
    LOSE("패") {
        @Override
        public GameOutcome oppositeOutcome() {
            return WIN;
        }
    },
    ;

    private final String value;

    GameOutcome(String value) {
        this.value = value;
    }

    public static GameOutcome of(int playerScore, int dealerScore) {
        if (playerScore > 21) {
            return LOSE;
        }
        if (dealerScore > 21) {
            return WIN;
        }
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore < dealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    public abstract GameOutcome oppositeOutcome();

    public String value() {
        return value;
    }
}
