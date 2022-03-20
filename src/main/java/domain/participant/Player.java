package domain.participant;

import domain.GameResult;
import domain.HitThreshold;

public final class Player extends Participant {

    public Player(final String name) {
        super(HitThreshold.PLAYER_THRESHOLD, name);
    }

    @Override
    public GameResult getGameResult(final Participant dealer) {
        return GameResult.getPlayerResult(this.cards, dealer.cards);
    }
}
