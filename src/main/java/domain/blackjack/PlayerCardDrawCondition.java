package domain.blackjack;

import domain.card.CardDrawCondition;

public final class PlayerCardDrawCondition implements CardDrawCondition {
    private final Gamer player;

    public PlayerCardDrawCondition(Gamer player) {
        this.player = player;
    }

    @Override
    public boolean canDraw() {
        return !player.isDead();
    }
}
