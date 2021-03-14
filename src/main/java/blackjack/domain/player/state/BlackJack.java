package blackjack.domain.player.state;

import blackjack.domain.card.Deck;

public class BlackJack implements State {

    @Override
    public boolean drawable() {
        return false;
    }

    @Override
    public int winningMoney(int batMoney) {
        return (int) (batMoney * 1.5);
    }

    @Override
    public State currentState(Deck deck) {
        return this;
    }

}
