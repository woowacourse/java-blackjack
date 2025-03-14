package model.participant;

import java.util.List;
import model.card.Card;

public class Participant {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;
    private final ParticipantHand participantHand;

    public Participant() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public boolean isBust() {
        return participantHand.checkBust();
    }

    public boolean checkBlackjack() {
        return getParticipantHand().checkBlackjack();
    }

    public int calculateFinalScore() {
        return participantHand.calculateFinalScore();
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }
}
