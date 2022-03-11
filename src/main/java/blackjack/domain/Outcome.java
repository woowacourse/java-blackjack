package blackjack.domain;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public static Outcome compare(Gamer gamer1, Gamer gamer2) {
        if (gamer1.compareWinning(gamer2) > 0) {
            return WIN;
        }
        if (gamer1.compareWinning(gamer2) < 0) {
            return LOSE;
        }
        return DRAW;
    }

    public String get() {
        return outcome;
    }
}
