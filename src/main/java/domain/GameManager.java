package domain;

import domain.card.CardsSnapshot;
import domain.participant.Participant;
import domain.participant.Participants;

public class GameManager {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;

    private GameManager(Deck deck) {
        this.deck = deck;
    }

    public static GameManager createWith(Deck deck) {
        return new GameManager(deck);
    }

    public void dealCard(Participant participant) {
        participant.receiveCard(deck.draw());
    }

    public CardsSnapshot getCardsResult(Participant participant) {
        return participant.handInfo();
    }

    public void dealInitialCards(Participants participants) {
        participants.distributeCardsToAll(deck, INITIAL_CARD_COUNT);
    }
}
