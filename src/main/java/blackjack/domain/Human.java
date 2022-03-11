package blackjack.domain;

import java.util.List;

public abstract class Human {

    protected final Cards cards = new Cards();

    public List<String> showCards() {
        return cards.getAllCards();
    }

    public int showSumOfCards() {
        return cards.calculateTotal();
    }

    public void receiveInitCards(final List<Card> initCards) {
        cards.add(initCards);
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    abstract boolean isReceived();
}
