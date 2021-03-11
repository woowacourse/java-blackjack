package blackjack.domain.state;

import blackjack.domain.card.Cards;

public class Bust implements State {
    private final Cards cards;

    public Bust(final Cards cards) {
        this.cards = cards;
    }
}
