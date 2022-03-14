package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class CurrentTurnParticipant {

    private final String name;
    private final List<Card> cards;

    public CurrentTurnParticipant(final Participant participant) {
        this.name = participant.getName();
        this.cards = participant.getInitCards();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
