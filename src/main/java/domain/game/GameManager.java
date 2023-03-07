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

    public int getParticipantSize() {
        return participants.size();
    }


    public void handFirstCards(final int participantOrder) {
        participants.addCard(participantOrder, deck.draw(), deck.draw());
    }

    public void handCard(final int participantOrder) {
        participants.addCard(participantOrder, deck.draw());
    }
}
