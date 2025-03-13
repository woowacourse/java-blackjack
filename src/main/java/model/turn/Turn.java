package model.turn;

import model.card.Deck;
import model.participant.Participant;

public class Turn {
    private final Participant participant;

    public Turn(Participant participant) {
        this.participant = participant;
    }

    public void dealInitialCards(Deck deck) {
        for (int i = 0; i < 2; i++) {
            participant.receiveCard(deck.pick());
        }
    }

}
