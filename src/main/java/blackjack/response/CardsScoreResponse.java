package blackjack.response;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import java.util.List;
import java.util.stream.Collectors;

public class CardsScoreResponse {

    private final List<CardResponse> cards;
    private final int score;

    private CardsScoreResponse(final List<CardResponse> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public static CardsScoreResponse from(final Dealer dealer) {
        return new CardsScoreResponse(convertCards(dealer.getCards()), dealer.currentScore());
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
