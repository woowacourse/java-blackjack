package blackjack.domain;

import blackjack.domain.player.Player;

public enum Outcome {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String outcome;

    Outcome(String outcome) {
        this.outcome = outcome;
    }

    public static Outcome compare(Player player1, Player player2) {
        if (player1.compareWinning(player2) > 0) {
            return WIN;
        }
        if (player1.compareWinning(player2) < 0) {
            return LOSE;
        }
        return DRAW;
    }

    public String get() {
        return outcome;
    }
}
