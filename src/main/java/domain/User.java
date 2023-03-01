package domain;

import java.util.List;

public abstract class User {

    private final Cards cards = new Cards();

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
