package blackjack.response;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import java.util.List;
import java.util.stream.Collectors;

public class DealerScoreResponse {

    private final List<CardResponse> cards;
    private final int score;

    private DealerScoreResponse(final List<CardResponse> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public static DealerScoreResponse from(final Dealer dealer) {
        return new DealerScoreResponse(convertCards(dealer.getCards()), dealer.currentScore());
    }

    private static List<CardResponse> convertCards(
            final List<Card> cards) {
        return cards.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public List<CardResponse> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
