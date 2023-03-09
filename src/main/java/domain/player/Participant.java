package domain.player;

import domain.deck.Card;

import java.util.List;

public abstract class Participant {

    private final Hand hand;

    public Participant(final Hand hand) {
        this.hand = hand;
    }

    public void hit(final Card card) {
        hand.addCard(card);
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.score();
    }
}
