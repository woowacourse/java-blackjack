package domain;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public static Outcome calculate(int dealerScore, int playerScore) {
        if (dealerScore < playerScore) {
            return WIN;
        }
        if (dealerScore == playerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public static Outcome converseOutcome(Outcome outcome) {
        if (outcome == Outcome.DRAW) {
            return Outcome.DRAW;
        }
        return outcome == Outcome.WIN ? Outcome.LOSE : Outcome.WIN;
    }

    public String getName() {
        return name;
    }
}
