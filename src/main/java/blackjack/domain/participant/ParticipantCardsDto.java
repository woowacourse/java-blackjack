package blackjack.domain.participant;

import java.util.List;

import blackjack.domain.card.Card;

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
