package blackjack.view.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participant;

public class ParticipantResponse {

    private final String name;
    private final CardsResponse cardsResponse;

    private ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public static ParticipantResponse from(final Participant participant) {
        final String name = participant.getName();
        final Cards cards = participant.getCards();
        return new ParticipantResponse(name, CardsResponse.from(cards));
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
