package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;

public abstract class Gamer {

    protected final Hand hand;

    public Gamer() {
        this.hand = new Hand(new ArrayList<>());
    }

    public abstract boolean canHit();

    public void drawTwoCards(final Deck deck) {
        draw(deck.pop());
        draw(deck.pop());
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        return hand.sum();
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
