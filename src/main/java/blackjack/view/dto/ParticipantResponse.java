package blackjack.view.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantResponse {

    private final String name;
    private final CardsResponse cardsResponse;

    private ParticipantResponse(final String name, final CardsResponse cardsResponse) {
        this.name = name;
        this.cardsResponse = cardsResponse;
    }

    public static ParticipantResponse from(final Participant participant) {
        final CardsResponse cardsResponse = CardsResponse.of(participant.getScore(), participant.getCards());
        return new ParticipantResponse(participant.getName(), cardsResponse);
    }

    public static ParticipantResponse hiddenForDealer(final Dealer dealer, final int count) {
        final List<Card> hiddenCards = dealer.getCards().subList(0, count - 1);
        final CardsResponse cardsResponse = CardsResponse.of(-1, hiddenCards);
        return new ParticipantResponse(dealer.getName(), cardsResponse);
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
