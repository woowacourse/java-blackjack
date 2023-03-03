package blackjack.view.dto;

import blackjack.domain.participant.Participant;

public class ParticipantResponse {

    private final String name;
    private final CardsResponse cardsResponse;

    private ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public static ParticipantResponse from(final Participant participant) {
        return new ParticipantResponse(
                participant.getName(), CardsResponse.of(participant.getScore(), participant.getCards())
        );
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
