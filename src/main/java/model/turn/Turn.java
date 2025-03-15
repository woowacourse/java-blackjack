package model.turn;

import model.card.Deck;
import model.participant.Participant;

public class Turn {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    protected final Participant participant;

    public Turn(Participant participant) {
        this.participant = participant;
    }

    public void dealInitialCards(Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.pick());
        }
    }
}
