package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.user.status.Hand;
import blackjack.domain.user.status.Status;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    protected final Hand hand;
    protected final ParticipantName participantName;
    protected Status status;

    public Participant(String name) {
        this.hand = Hand.createEmptyHand();
        this.participantName = new ParticipantName(name);
    }

    public void drawCard(Card card) {
        hand.add(card);
        changeStatus();
    }

    public void drawCards(List<Card> cards) {
        cards.forEach(this::drawCard);
    }

    public Cards getCards() {
        return hand.getCards();
    }

    public boolean isSameHandSize(int size) {
        return hand.size() == size;
    }

    public boolean isSameStatus(Status status) {
        return this.status == status;
    }

    public boolean isNotStatus(Status status) {
        return this.status != status;
    }

    public boolean scoreBiggerThan(Participant participant) {
        return this.calculateScore() > participant.calculateScore();
    }

    public boolean isSameScore(Participant participant) {
        return this.calculateScore() == participant.calculateScore();
    }

    public boolean scoreSmallerThan(Participant participant) {
        return this.calculateScore() < participant.calculateScore();
    }

    public void changeStatus() {
        status = Status.of(this);
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public ParticipantName getName() {
        return participantName;
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
