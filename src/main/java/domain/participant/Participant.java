package domain.participant;

import domain.Score;
import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class Participant {
    protected final Name name;
    protected final Hand hand;

    public Participant(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public Participant(Name name, Hand hand) {
        validate(name, hand);
        this.name = name;
        this.hand = hand;
    }

    private void validate(Name name, Hand hand) {
        if (name == null || hand == null) {
            throw new IllegalArgumentException(Card.FIELD_CAN_NOT_BE_NULL);
        }
    }

    public boolean isBust() {
        return hand.totalSum().isBust();
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

    public Name getName() {
        return name;
    }

    public Hand getCards() {
        return hand;
    }
}
