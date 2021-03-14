package blackjack.domain.player.state;

import blackjack.domain.card.Deck;

public class Hit implements State {

    @Override
    public boolean drawable() {
        return true;
    }

    @Override
    public int winningMoney(int batMoney) {
        return batMoney;
    }

    @Override
    public State currentState(Deck deck) {
        if (deck.isBust()) {
            return new Bust();
        }
        return this;
    }
}
