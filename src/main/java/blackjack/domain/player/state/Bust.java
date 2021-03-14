package blackjack.domain.player.state;

import blackjack.domain.card.Deck;

public class Bust implements State {

    @Override
    public boolean drawable() {
        return false;
    }

    @Override
    public int winningMoney(int batMoney) {
        return -batMoney;
    }

    @Override
    public State currentState(Deck deck) {
        return this;
    }

}
