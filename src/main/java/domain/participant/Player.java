package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;

import java.util.List;

public class Player {
    protected final Hand hand = new Hand();
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public int getFinalScore() {
        return hand.calculateFinalScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public void receiveInitialCards(Deck deck) {
        hand.add(deck.pop());
        hand.add(deck.pop());
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return hand.getSize();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
