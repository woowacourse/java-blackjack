package model.participant;

import java.util.Set;
import model.result.BettingResults;
import model.bet.ParticipantsBet;
import model.cards.DealerCards;
import model.cards.ParticipantsCards;
import model.cards.PlayerCards;
import model.result.GameResults;

public class Participants {

    private final ParticipantsCards participantsCards;
    private final ParticipantsBet participantsBet;

    public Participants(final ParticipantsCards participantsCards, final ParticipantsBet participantsBet) {
        this.participantsCards = participantsCards;
        this.participantsBet = participantsBet;
    }

    public DealerCards getDealerCards() {
        return participantsCards.getDealerCards();
    }

    public PlayerCards findCardsByName(final String name) {
        return participantsCards.findCardsByName(name);
    }

    public BettingResults calculateBettingResults(final GameResults gameResults) {
        return participantsBet.calculateBettingResults(gameResults);
    }

    public Set<String> getPlayerNames() {
        return participantsCards.getNames();
    }
}
