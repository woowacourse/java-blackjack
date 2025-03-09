package model.participant;

import java.util.ArrayList;
import model.card.Card;
import model.card.Cards;

public abstract class Participant {
    private static final int BUST_THRESHOLD = 21;

    protected final Cards cards;

    protected Participant() {
        this.cards = new Cards(new ArrayList<>());
    }

    public void receiveCard(final Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateResult();
    }

    public boolean isNotBust() {
        return cards.calculateResult() <= BUST_THRESHOLD;
    }

    public Cards getCards() {
        return cards;
    }

    protected abstract boolean canDrawMore();
}
