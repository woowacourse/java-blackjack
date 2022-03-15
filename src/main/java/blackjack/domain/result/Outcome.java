package blackjack.domain.result;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public String get() {
        return outcome;
    }
}
