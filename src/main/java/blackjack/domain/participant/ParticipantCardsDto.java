package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class ParticipantCardsDto {
    private final Name name;
    private final List<Card> cards;

    private ParticipantCardsDto(Name name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantCardsDto from(Participant participant) {
        return new ParticipantCardsDto(participant.getName(), participant.showCards());
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
