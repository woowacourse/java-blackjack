package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Participant {

    protected final Cards cards;
    protected boolean drawable;

    protected Participant() {
        this(new Cards());
    }

    protected Participant(final Cards cards) {
        this.cards = cards;
        this.drawable = true;
    }

    public void drawCard(final Card card) {
        cards.addCard(card);
    }

    public void changeDrawable() {
        drawable = !drawable;
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public Cards getCards() {
        return cards;
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public abstract boolean isDrawable();

    public abstract String getName();
}
