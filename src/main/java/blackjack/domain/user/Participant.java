package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public abstract class Participant {
    protected final Hand hand;
    protected final Name name;
    protected Status status;

    public Participant(Name name) {
        this.hand = Hand.createEmptyHand();
        this.name = name;
    }

    public void drawCard(Card card) {
        hand.add(card);
        changeStatus();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public int handSize() {
        return hand.size();
    }

    public boolean isSameStatus(Status status) {
        return this.status == status;
    }

    public void changeStatus() {
        status = Status.of(hand.calculateScore());
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public String getName() {
        return name.getName();
    }

    public boolean isNotStatus(Status status) {
        return this.status != status;
    }
}
