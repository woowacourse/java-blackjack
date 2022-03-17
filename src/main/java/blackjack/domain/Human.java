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

    boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isWinner(final Human human) {
        if (human.isSameTotal(getTotal())) {
            return isBlackjack();
        }
        return human.isBust() || human.hasTotalLowerThan(getTotal());
    }

    boolean isSameTotal(int total) {
        return getTotal() == total;
    }

    boolean hasTotalLowerThan(int total) {
        return getTotal() < total;
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
