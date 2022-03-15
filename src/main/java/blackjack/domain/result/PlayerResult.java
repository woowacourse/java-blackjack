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

    public static PlayerResult createResult(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if (player.isBust()) {
            return LOSS;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        return compareScore(playerScore, dealerScore);
    }

    private static PlayerResult compareScore(Score playerScore, Score dealerScore) {
        if (playerScore.isOver(dealerScore)) {
            return WIN;
        }

        if (playerScore.equals(dealerScore)) {
            return DRAW;
        }

        return LOSS;
    }

    public String getName() {
        return name;
    }
}
