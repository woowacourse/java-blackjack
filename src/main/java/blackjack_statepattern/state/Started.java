package blackjack_statepattern.state;

import blackjack_statepattern.card.Cards;

public abstract class Started implements State{

    private final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
