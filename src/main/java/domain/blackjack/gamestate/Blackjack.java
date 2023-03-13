package domain.blackjack.gamestate;

import domain.card.Cards;

public class Blackjack extends GameState {

    Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public GameState drawCard() {
        return null;
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return false;
    }

    @Override
    public double getEarningRate() {
        return 0;
    }
}
