package domain.card;

import domain.participant.Participants;

public class CardManager {

    private final Deck deck;
    private final Participants participants;

    private CardManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static CardManager create(final Deck deck, final Participants participants) {
        return new CardManager(deck, participants);
    }

    public void giveCards(final int participantOrder, final int givenCount) {
        int cardCount = 0;

        while (cardCount++ < givenCount) {
            participants.addCard(participantOrder, deck.draw());
        }
    }
}
