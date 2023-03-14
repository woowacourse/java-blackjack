package domain.participant;

import domain.card.Card;
import domain.card.Score;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected static final ParticipantName DEALER_NAME = ParticipantName.create("딜러");

    protected final ParticipantCard hand;
    private final ParticipantName name;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
        this.hand = ParticipantCard.create();
    }

    public static Participant createDealer() {
        return Dealer.create();
    }

    public final void addCard(final Card... cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    public final int calculateScore() {
        Score score = hand.calculateScore();
        return score.getScore();
    }

    public final boolean isBust() {
        return hand.isBust();
    }

    public final boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public abstract Card getFirstCard();

    public abstract boolean canGiveCard();

    public final String getName() {
        return name.getName();
    }

    public final List<Card> getHand() {
        return List.copyOf(hand.getCards());
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
}
