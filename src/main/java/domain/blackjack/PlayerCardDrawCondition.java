package domain.blackjack;

import domain.card.CardDrawCondition;

public final class PlayerCardDrawCondition implements CardDrawCondition {
    private final BlackJackGameMachine player;

    public PlayerCardDrawCondition(BlackJackGameMachine player) {
        this.player = player;
    }

    @Override
    public boolean canDraw() {
        return !player.isBust();
    }
}
