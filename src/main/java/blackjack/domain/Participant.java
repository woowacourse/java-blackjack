package blackjack.domain;

import java.util.List;

public abstract class Participant {

    protected final Hand hand = new Hand();
    private boolean blackJack;

    public void take(Card card) {
        hand.add(card);
    }

    public void handInitialCards(Deck deck) {
        take(deck.draw());
        take(deck.draw());
        blackJack = hand.isBlackJack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getSum() {
        return hand.getSum();
    }

    public abstract boolean canHit();
}
