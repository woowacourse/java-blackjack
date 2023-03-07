package domain.game;

import domain.card.Deck;
import domain.participant.Participants;
import java.util.stream.IntStream;

public final class GameManager {

    private static final int START_CARD_COUNT = 2;

    private final Deck deck;
    private final Participants participants;

    private GameManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final Deck deck, final Participants participants) {
        return new GameManager(deck, participants);
    }

    public void giveStartCards() {
        final int totalParticipantSize = participants.size();

        for (int participantOrder = 0; participantOrder < totalParticipantSize; participantOrder++) {
            giveCards(participantOrder, START_CARD_COUNT);
        }
    }

    public void giveCards(final int participantOrder, final int givenCount) {
        int cardCount = 0;

        while (cardCount++ < givenCount) {
            participants.addCard(participantOrder, deck.draw());
        }
    }
}
