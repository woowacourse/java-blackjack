package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantCards {

    private final String name;
    private final List<Card> cards;

    private ParticipantCards(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantCards toParticipantFirstCards(final Participant participant) {
        return new ParticipantCards(
                participant.getName(),
                participant.firstCards()
        );
    }

    public static ParticipantCards toParticipantCards(final Participant participant) {
        return new ParticipantCards(
                participant.getName(),
                participant.cards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
