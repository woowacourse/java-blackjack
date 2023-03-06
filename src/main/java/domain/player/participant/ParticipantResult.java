package domain.player.participant;

import domain.player.dealer.DealerResult;

public enum ParticipantResult {
    WINNER,
    LOSER,
    DRAWER;

    public DealerResult convertToDealerResult() {
        if (this == WINNER) {
            return DealerResult.LOSER;
        }
        if (this == LOSER) {
            return DealerResult.WINNER;
        }
        return DealerResult.DRAWER;
    }
}
