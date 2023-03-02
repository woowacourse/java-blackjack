package blackjackgame.domain;

public enum GameOutcome {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String outcome;

    GameOutcome(final String outcome) {
        this.outcome = outcome;
    }

    public String getOutcome() {
        return outcome;
    }
}
