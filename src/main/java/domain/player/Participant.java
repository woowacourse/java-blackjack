package domain.player;

import domain.deck.Card;

import java.util.List;

public abstract class Participant {

    private static final int BLACK_JACK_NUMBER = 21;

    private final Hand hand;

    public Participant(final Hand hand) {
        this.hand = hand;
    }

    public void hit(final Card card) {
        hand.addCard(card);
    }

    public boolean isBlackJack() {
        return getCards().size() == 2 && getScore() == BLACK_JACK_NUMBER;
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int getScore() {
        return hand.score();
    }
}
