package domain;

public enum Outcome {
    WIN("승"), DRAW("무"), LOSE("패");

    private final String outcome;

    Outcome(final String outcome) {
        this.outcome = outcome;
    }
}
