package domain;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    UNDEFINED("미정");

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

    public String getName() {
        return name;
    }
}
