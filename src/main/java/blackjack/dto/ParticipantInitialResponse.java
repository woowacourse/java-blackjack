package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantInitialResponse {

    private final String name;
    private final List<Card> cards;

    public ParticipantInitialResponse(Participant participant) {
        this.name = participant.getName();
        this.cards = List.copyOf(participant.showInitialCards());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
