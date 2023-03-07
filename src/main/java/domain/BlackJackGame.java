package domain;

import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public final class BlackJackGame {

    private final Participants participants;
    private final Cards cards;

    public BlackJackGame(final Participants participants, final Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public void giveCardTo(Participant participant) {
        participant.receiveCard(cards.getCard());
    }

    public Participants getParticipants() {
        return participants;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
