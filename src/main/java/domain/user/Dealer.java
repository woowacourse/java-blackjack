package domain.user;

import domain.game.Score;

public class Dealer extends Player {

    private static final Score DEALER_HIT_LIMIT = new Score(16);

    public Dealer(String playerName, Hand hand) {
        super(playerName, hand);
    }

    public boolean canHit() {
        return sumHand().isLessThanOrEqual(DEALER_HIT_LIMIT);
    }
}
