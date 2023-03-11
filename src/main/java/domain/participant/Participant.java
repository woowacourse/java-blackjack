package domain.participant;

import java.util.List;

import domain.card.Card;

public abstract class Participant {

    protected Name name;
    protected Hand hand;

    public Participant(Name name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void takeCard(Card card) {
        hand.addCard(card);
    }

    public int calculateOptimalCardValueSum() {
        return hand.calculateOptimalCardValueSum();
    }

    public String getName() {
        return name.getValue();
    }

    public Hand getHand() {
        return hand;
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }
}
