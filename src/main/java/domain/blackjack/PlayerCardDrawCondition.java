package domain.blackjack;

import domain.card.CardDrawCondition;

final class PlayerCardDrawCondition implements CardDrawCondition {
    private final BlackJackGameMachine player;

    PlayerCardDrawCondition(BlackJackGameMachine player) {
        this.player = player;
    }

    @Override
    public boolean canDraw() {
        return !player.isBust();
    }
}
