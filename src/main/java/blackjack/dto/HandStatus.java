package blackjack.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class HandStatus {

    private final String participantName;
    private final List<Card> cards;

    public HandStatus(final String participantName, final List<Card> cards) {
        this.participantName = participantName;
        this.cards = cards;
    }

    public String getParticipantName() {
        return participantName;
    }

    public List<Card> getCards() {
        return cards;
    }
}
