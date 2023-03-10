package domain.status;

import domain.card.Cards;

public class Stand extends Status {

    private final Cards cards;

    public Stand(final Cards cards) {
        this.cards = cards;
    }
}
