package domain.participant;

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

    public String getName() {
        return name.getValue();
    }

    public Hand getHandCards() {
        return hand;
    }

    public int getSize() {
        return hand.getSize();
    }
}
