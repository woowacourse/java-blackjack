package blackjack.domain.player;

public class Player extends User {

    public Player(final PlayerName playerName) {
        super(playerName);
    }

    @Override
    public boolean canHit() {
        return isHitState();
    }

    public boolean isNotBust() {
        return !isBurstState();
    }
}
