package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK = 21;
    private static final int INITIAL_HAND_SIZE = 2;

    private final String name;
    private final Hand hand;

    protected Participant(String name, Deck deck) {
        this.hand = Hand.from(deck);
        this.name = name;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int calculateScore() {
        return hand.getScore();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public boolean isBlackjack() {
        return getCards().size() == INITIAL_HAND_SIZE && calculateScore() == BLACKJACK;
    }

    public abstract boolean isStand();

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.toList());
    }
}
