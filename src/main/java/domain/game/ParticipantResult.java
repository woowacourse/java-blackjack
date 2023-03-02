package domain.game;

import domain.player.Dealer;
import domain.player.Player;

public enum ParticipantResult {
    WINNER,
    LOSER,
    DRAWER;

    public static ParticipantResult judge(final Player player, final Dealer dealer) {
        if (player.isBurst()) {
            return ParticipantResult.LOSER;
        }
        if (dealer.isBurst()) {
            return ParticipantResult.WINNER;
        }
        if (player.score() > dealer.score()) {
            return ParticipantResult.WINNER;
        }
        if (player.score() == dealer.score()) {
            return ParticipantResult.DRAWER;
        }
        return ParticipantResult.LOSER;
    }

    public ParticipantResult reverse() {
        if (this == WINNER) {
            return LOSER;
        }
        if (this == LOSER) {
            return WINNER;
        }
        return this;
    }
}