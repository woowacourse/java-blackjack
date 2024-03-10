package domain.player;

import domain.player.Name;
import domain.player.Player;

public class Dealer extends Player {

    private static final int HIT_THRESHOLD = 17;

    public Dealer() {
        super(Name.DEALER_NAME);
    }

    @Override
    public boolean isBust() {
        return calculateScore() > PERFECT_SCORE;
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_THRESHOLD;
    }

    @Override
    public boolean canNotHit() {
        return false;
    }
}
