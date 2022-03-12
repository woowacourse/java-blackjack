package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;

public class BlackjackGame {

    private final Deck deck = new Deck();
    private final Participants participants;

    public BlackjackGame(Participants participants) {
        this.participants = participants;
    }

    public void initCards() {
        participants.dealInitialCards(deck);
    }

    public void hitCard(Participant participant) {
        participant.addCard(deck.pickCard());
    }

    public Participants getParticipants() {
        return participants;
    }
}
