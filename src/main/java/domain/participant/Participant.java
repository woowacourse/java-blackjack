package domain.participant;

import domain.card.Card;

public abstract class Participant {
    private final ParticipantInfo participantInfo;

    public Participant(ParticipantInfo participantInfo) {
        this.participantInfo=participantInfo;
    }

    public abstract void keepCard(Card card);

    public abstract boolean canHit();

    public int handSize() {
        return participantInfo.handSize();
    }

    public int getTotalCardScore() {
        return participantInfo.getTotalCardScore();
    }

    public String getName() {
        return participantInfo.getName();
    }

    public Hand getHand() {
        return participantInfo.getHand();
    }
}
