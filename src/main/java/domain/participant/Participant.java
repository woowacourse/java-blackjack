package domain.participant;

import constant.GameConstant;
import domain.card.Card;
import domain.card.Deck;
import domain.card.Hand;

import java.util.List;

public abstract class Participant {
    protected final Hand hand = new Hand();
    protected final String name;

    public Participant(String name) {
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

    public boolean isBlackjack() {
        return hand.getSize() == GameConstant.NUM_OF_TO_BLACKJACK && getFinalScore() == GameConstant.BLACKJACK_SCORE;
    }
}
