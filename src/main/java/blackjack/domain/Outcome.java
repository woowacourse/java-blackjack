package blackjack.domain;

public enum Outcome {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Outcome(String name) {
        this.name = name;
    }

    public static Outcome calculate(int dealerScore, int playerScore) {
        if (dealerScore == playerScore) {
            return DRAW;
        }
        return dealerScore < playerScore ? WIN : LOSE;
    }

    public static Outcome converseOutcome(Outcome outcome) {
        if (outcome == DRAW) {
            return DRAW;
        }
        return outcome == WIN ? LOSE : WIN;
    }

    public String getName() {
        return name;
    }
}
