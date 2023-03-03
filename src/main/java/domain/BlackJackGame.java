package domain;

import domain.card.Cards;
import domain.participant.Participant;
import domain.participant.Participants;

public class BlackJackGame {

    private Participants participants;
    private Cards cards;

    public BlackJackGame(final Participants participants, final Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public void giveCardTo(Participant participant){
        participant.receiveCard(cards.getCard());
    }

    public Participants getParticipants() {
        return participants;
    }
}
