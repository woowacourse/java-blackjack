package blackjack.domain;

import java.util.List;

public abstract class Human {

    protected String name;

    protected Human(final String name) {
        this.name = name;
    }

    abstract List<String> getCards();

    abstract int getTotal();

    abstract void dealInit(List<Card> initCards);

    abstract void hit(Card card);

    abstract boolean isBust();

    abstract boolean canDraw();
}
