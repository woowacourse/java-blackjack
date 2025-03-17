package model.turn;

import model.card.Deck;
import model.participant.Participant;

public interface Turn {
    int INITIAL_DEAL_CARD_COUNT = 2;

    void dealInitialCards(Deck deck);

    default void dealCards(Participant participant, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.pick());
        }
    }
}
