package domain.participant;

import domain.card.Card;

public final class Participant {

    static final String DEALER_NAME = "딜러";

    private final ParticipantName participantName;
    private final ParticipantCard participantCard;

    private Participant(final ParticipantName participantName, final ParticipantCard participantCard) {
        this.participantName = participantName;
        this.participantCard = participantCard;
    }

    public static Participant create(final String name) {
        final ParticipantName participantName = ParticipantName.create(name);
        final ParticipantCard participantCard = ParticipantCard.create();

        return new Participant(participantName, participantCard);
    }

    void addCard(final Card drawCard) {
        participantCard.addCard(drawCard);
    }

    ParticipantScore calculateScore() {
        return participantCard.calculateScore();
    }

    public boolean canDraw(final int score) {
        return participantCard.canDraw(score);
    }

    boolean checkBust() {
        return participantCard.checkBust();
    }

    boolean checkBlackJack() {
        return participantCard.checkBust();
    }

    ParticipantCard participantCard() {
        return participantCard;
    }

    public String getName() {
        return participantName.getName();
    }
}
