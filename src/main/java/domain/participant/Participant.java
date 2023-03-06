package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK = 21;

    private final String name;
    private final Hand hand;

    protected Participant(String name, Hand hand) {
        this.hand = hand;
        this.name = name;
    }

    public static Participant dealer(Deck deck) {
        return Dealer.from(Hand.from(deck));
    }

    public static Participant player(String name, Deck deck) {
        return Player.from(name, Hand.from(deck));
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

    public abstract boolean isStand();

    public abstract void stand();

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(hand.toList());
    }
}
