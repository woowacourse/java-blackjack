package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class AbstractBlackjackGameState implements BlackjackGameState {

    private final Cards cards;

    public AbstractBlackjackGameState(final Cards cards) {
        this.cards = cards;
    }
}
