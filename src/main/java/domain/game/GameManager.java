package domain.game;

import domain.card.Deck;
import domain.participant.Participants;

public class GameManager {

    private final Deck deck;
    private final Participants participants;

    private GameManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final Deck deck, final Participants participants) {
        return new GameManager(deck, participants);
    }

    public void giveCards(final int participantOrder, final int givenCount) {
        int cardCount = 0;

        while (cardCount++ < givenCount) {
            participants.addCard(participantOrder, deck.draw());
        }
    }
}
