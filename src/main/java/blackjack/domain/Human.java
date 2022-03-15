package blackjack.domain;

import java.util.List;

public abstract class Human {

    protected final String name;
    protected final PlayingCards cards = new PlayingCards();

    protected Human(final String name) {
        this.name = name;
    }

    public void dealInit(List<Card> initCards) {
        cards.add(initCards);
    }

    public void hit(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return cards.isOverBlackjack();
    }

    abstract boolean canDraw();

    public List<String> getCards() {
        return cards.getAllNames();
    }

    public int getTotal() {
        return cards.calculateTotal();
    }

    public String getName() {
        return name;
    }
}
