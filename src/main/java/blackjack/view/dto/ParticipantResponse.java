package blackjack.view.dto;

public class ParticipantResponse {

    private final String name;
    private final CardsResponse cardsResponse;

    public ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
