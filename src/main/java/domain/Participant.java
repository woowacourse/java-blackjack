package domain;

import java.util.Collections;
import java.util.List;

public abstract class Participant {
    protected Cards cards = new Cards();;

    public void addInitializedCard(Deck deck) {
        add(deck.pop());
        add(deck.pop());
    }

    public void add(Card card) {
        cards.addCard(card);
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards.getCards());
    }

    public List<String> getCardsDisplay() {
        return Collections.unmodifiableList(cards.getCardsDisplay());
    }

    public int getCardsTotalSum() {
        return cards.getFinalScore();
    }


}
