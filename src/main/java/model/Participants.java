package model;

import java.util.Set;
import model.bet.ParticipantsBet;
import model.cards.DealerCards;
import model.cards.ParticipantsCards;
import model.cards.PlayerCards;

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

    public Set<String> getPlayerNames() {
        return participantsCards.getNames();
    }
}
