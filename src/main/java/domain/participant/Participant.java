package domain.participant;

import domain.card.Card;
import domain.card.Score;

import java.util.List;
import java.util.Objects;

public class Participant {

    protected static final ParticipantName DEALER_NAME = ParticipantName.create("딜러");
    protected final ParticipantCard hand;
    private final ParticipantName name;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
        this.hand = ParticipantCard.create();
    }

    static Participant create(final String name) {
        return new Participant(name);
    }

    void addCard(final Card... cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    boolean isBust() {
        return hand.isBust();
    }

    boolean isBlackJack() {
        return hand.isBlackJack();
    }

    Score calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (!(target instanceof Participant)) {
            return false;
        }
        Participant targetParticipant = (Participant) target;
        return Objects.equals(name, targetParticipant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    String getName() {
        return name.getName();
    }

    List<Card> getHand() {
        return List.copyOf(hand.getCards());
    }
}
