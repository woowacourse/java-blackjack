package domain.participant;

import domain.card.Card;

import java.util.List;

public class Participant {

    protected static final ParticipantName DEALER_NAME = ParticipantName.create("딜러");

    private final ParticipantName name;
    protected final ParticipantCard participantCard;

    protected Participant(final String name) {
        this.name = ParticipantName.create(name);
        participantCard = ParticipantCard.create();
    }

    public final void addCard(final Card card) {
        this.participantCard.addCard(card);
    }

    public final List<Card> getCard() {
        return List.copyOf(participantCard.getCards());
    }

    public final int calculateScore() {
        return participantCard.calculateScore();
    }

    public final boolean isBust() {
        return participantCard.isBust();
    }

    public final boolean isBlackJack() {
        return participantCard.isBlackJack();
    }

    public final String getName() {
        return name.getName();
    }
}
