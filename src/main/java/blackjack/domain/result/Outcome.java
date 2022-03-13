package blackjack.domain.result;

import blackjack.domain.gamer.Gamer;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public static Outcome compare(Gamer dealer, Gamer player) {
        if (dealer.compare(player) > 0) {
            return WIN;
        }
        if (dealer.compare(player) < 0) {
            return LOSE;
        }
        return DRAW;
    }

    public String get() {
        return outcome;
    }
}
