package model;

import java.util.List;

public class Participant {
    private final ParticipantHand participantHand;

    public Participant() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public void dealInitialCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            receiveCard(deck.pick());
        }
    }

    public boolean isBurst() {
        return participantHand.checkBurst();
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
