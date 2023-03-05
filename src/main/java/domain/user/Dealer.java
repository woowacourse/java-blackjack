package domain.user;

import domain.game.GameRule;

public class Dealer extends Player {

    public Dealer(String playerName, Hand hand) {
        super(playerName, hand);
    }

    public boolean isHit() {
        return sumHand() <= GameRule.DEALER_HIT_LIMIT.getNumber();
    }
}
