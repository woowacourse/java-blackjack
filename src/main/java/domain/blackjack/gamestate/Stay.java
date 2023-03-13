package domain.blackjack.gamestate;

import domain.card.Cards;

public class Stay extends GameState {

    Stay(Cards cards) {
        super(cards);
    }

    @Override
    public GameState drawCard() {
        return null;
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return true;
    }

    @Override
    public double getEarningRate() {
        return 0;
    }
}
