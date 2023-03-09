package domain;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.List;

public final class BlackJackGame {

    private final Participants participants;
    private final CardDeck cardDeck;

    public BlackJackGame(final Participants participants, final CardDeck cardDeck) {
        this.participants = participants;
        this.cardDeck = cardDeck;
    }

    public void giveCardTo(Participant participant) {
        participant.receiveCard(cardDeck.getCard());
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
