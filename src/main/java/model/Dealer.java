package model;

import java.util.List;

public class Dealer {
    private final ParticipantHand participantHand;

    public Dealer() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public boolean checkScoreUnderSixteen() {
        return participantHand.checkScoreBelow(16);
    }

    public void dealInitialCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            receiveCard(deck.pick());
        }
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }

    public ParticipantHand getParticipantHand() {
        return participantHand;
    }
}

