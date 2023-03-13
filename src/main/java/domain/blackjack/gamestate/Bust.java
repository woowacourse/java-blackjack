package domain.blackjack.gamestate;

import domain.card.Card;
import domain.card.Cards;

public class Bust extends GameState {
    Bust(Cards cards) {
        super(cards);
    }

    @Override
    public GameState receive(Card card) {
        throw new UnsupportedOperationException();
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
