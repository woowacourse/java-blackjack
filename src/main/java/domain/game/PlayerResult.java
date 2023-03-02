package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

public enum PlayerResult {
    WINNER,
    LOSER,
    DRAWER;

    public static PlayerResult judge(final Participant participant, final Dealer dealer) {
        if (participant.isBurst()) {
            return PlayerResult.LOSER;
        }
        if (dealer.isBurst()) {
            return PlayerResult.WINNER;
        }
        if (participant.score() > dealer.score()) {
            return PlayerResult.WINNER;
        }
        if (participant.score() == dealer.score()) {
            return PlayerResult.DRAWER;
        }
        return PlayerResult.LOSER;
    }

    public PlayerResult reverse() {
        if (this == WINNER) {
            return LOSER;
        }
        if (this == LOSER) {
            return WINNER;
        }
        return this;
    }
}