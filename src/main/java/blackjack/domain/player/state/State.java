package blackjack.domain.player.state;

import blackjack.domain.card.Deck;

public interface State {
    boolean drawable();

    int winningMoney(int batMoney);

    State currentState(Deck deck);
}