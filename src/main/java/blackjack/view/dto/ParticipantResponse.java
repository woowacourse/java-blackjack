package blackjack.view.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantResponse {

    private static final int HIDDEN_SCORE = -1;

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

    public static ParticipantResponse hiddenForDealer(final Dealer dealer) {
        final List<Card> hiddenCards = dealer.getHiddenCards();
        final CardsResponse cardsResponse = CardsResponse.of(HIDDEN_SCORE, hiddenCards);
        return new ParticipantResponse(dealer.getName(), cardsResponse);
    }

    public String getName() {
        return name;
    }

    public CardsResponse getCardsResponse() {
        return cardsResponse;
    }
}
