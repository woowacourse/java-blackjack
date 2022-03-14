package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;

    private ParticipantDto(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantDto from(final Participant participant) {
        return new ParticipantDto(
                participant.getName(),
                participant.getInitCards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
