package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final ParticipantHand participantHand;

    public Player() {
        this.participantHand = new ParticipantHand();
    }

    public void receiveCard(Card card) {
        participantHand.add(card);
    }

    public List<Card> getHandCards() {
        return participantHand.getCards();
    }
}
