package domain;

import java.util.List;

public abstract class User {

    protected final Cards cards = new Cards();

    public void hit(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    abstract boolean isHittable();
}
