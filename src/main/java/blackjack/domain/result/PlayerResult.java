package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum PlayerResult {

    WIN("승"),
    DRAW("무"),
    LOSS("패");

    private final String name;

    PlayerResult(final String name) {
        this.name = name;
    }

    public static PlayerResult makeResult(Player player) {
        if (player.isBust()) {
            return LOSS;
        }

        return WIN;
    }


    public static PlayerResult calculateResult(Score score, Score otherScore) {
        if (score.compareTo(otherScore) > 0) {
            return WIN;
        }
        if (score.compareTo(otherScore) == 0) {
            return DRAW;
        }
        return LOSS;
    }

    public String getName() {
        return name;
    }
}
