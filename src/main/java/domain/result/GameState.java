package domain.result;

import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.ParticipantGroup;
import domain.participant.Player;
import domain.participant.Players;

import java.util.List;

public class GameState {
    private final ParticipantGroup participantGroup;
    private final CardDeck cardDeck;

    public GameState(ParticipantGroup participantGroup, CardDeck cardDeck) {
        this.participantGroup = participantGroup;
        this.cardDeck = cardDeck;
    }

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public Players getPlayers() {
        return participantGroup.getPlayers();
    }

    public List<Player> findAllPlayers() {
        return List.copyOf(participantGroup.getPlayers().getAllPlayers());
    }

    public Dealer getDealer() {
        return participantGroup.getDealer();
    }
}
