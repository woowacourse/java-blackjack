package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Hand;
import domain.state.Blackjack;
import domain.state.Bust;
import domain.state.State;
import domain.state.Stay;
import java.util.List;

public abstract class Participant {
    protected final Hand hand;

    protected Participant() {
        this.hand = Hand.createEmpty();
    }

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public State getState() {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Stay(hand);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void addCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public Score getTotalSum() {
        return hand.totalSum();
    }

    public Hand getHand() {
        return hand;
    }

}
