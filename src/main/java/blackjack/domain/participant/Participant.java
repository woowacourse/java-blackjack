package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    protected final Cards cards;

    protected Participant() {
        this(new Cards());
    }

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public Cards getCards() {
        return cards;
    }

    public abstract boolean isDrawable();

    public abstract String getName();
}
