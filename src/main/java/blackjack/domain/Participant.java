package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand = new Hand();

    public void take(Card card) {
        hand.add(card);
    }

    public void handInitialCards(Deck deck) {
        take(deck.draw());
        take(deck.draw());
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getSum() {
        return hand.getSum();
    }

    public abstract boolean canHit();
}
