package model.participants;

import java.util.List;
import model.bettings.Wager;
import model.cards.Card;
import model.cards.Hand;

public abstract class Participant {
    protected Hand hand;
    protected Wager wager;

    public Participant() {
        this.hand = new Hand();
        this.wager = new Wager(0);
    }

    public abstract void calculateHandScore();

    public abstract boolean canHit();

    public void addCardToHand(Card card) {
        hand.addCards(List.of(card));
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }

    public int getHandScore() {
        return hand.getScore();
    }

    public int getWager() {
        return (int) wager.getWager();
    }
}
