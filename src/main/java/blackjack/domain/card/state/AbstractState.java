package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class AbstractState implements State {
    private final Cards cards;

    protected AbstractState(final Cards cards) {
        this.cards = cards;
    }

    protected Cards cards() {
        return this.cards;
    }

    public List<Card> getCards() {
        return this.cards.toList();
    }

    @Override
    public int calculate() {
        return this.cards.calculate();
    }

}
