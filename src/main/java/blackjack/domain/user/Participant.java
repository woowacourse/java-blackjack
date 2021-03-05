package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.user.status.Hand;
import blackjack.domain.user.status.Status;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Hand hand;
    protected final Name name;
    protected Status status;

    public Participant(Name name) {
        this.hand = Hand.createEmptyHand();
        this.name = name;
    }

    public void firstDraw(Card first, Card second) {
        drawCard(first);
        drawCard(second);
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

    public boolean isNotStatus(Status status) {
        return this.status != status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participant)) return false;
        Participant that = (Participant) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
