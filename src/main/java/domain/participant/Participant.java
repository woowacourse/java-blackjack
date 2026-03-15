package domain.participant;

import domain.hand.Hand;
import domain.card.Card;

import java.util.List;

public abstract class Participant {
    private final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    protected Hand getHand() {return hand;}

    public void addHandCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.getScore().isBust();
    }

    public int getScoreValue() {
        return hand.getScore().value();
    }

    public boolean isBlackjack(){
        return hand.isBlackjack();
    }

}
