package blackjack.domain.result;

import blackjack.domain.user.User;

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
