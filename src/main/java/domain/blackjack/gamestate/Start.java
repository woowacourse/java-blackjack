package domain.blackjack.gamestate;

import domain.card.Cards;

public class Start extends GameState {

    private Start(Cards cards) {
        super(cards);
    }

    public static GameState from(Cards cards) {
        return new Start(cards);
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
