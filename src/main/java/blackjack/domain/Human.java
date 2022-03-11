package blackjack.domain;

import java.util.List;

public abstract class Human {

    protected final Cards cards = new Cards();

    public List<Card> showCards() {
        return cards.getAllCards();
    }

    public int showSumOfCards() {
        return cards.calculateTotal();
    }

    public void drawInitCards(final List<Card> initCards) {
        cards.add(initCards);
    }

    public void drawCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    abstract boolean isDrawable();
}
