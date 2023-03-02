package blackjack.domain;

import java.util.List;

public abstract class Participant {

    private final Cards cards;

    protected Participant(final Cards cards) {
        this.cards = cards;
    }

    public void drawCard(final Card card) {
        cards.addCard(card);
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public abstract boolean isDrawable();

    public abstract String getName();
}
