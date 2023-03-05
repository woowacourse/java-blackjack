package blackjack.response;

import blackjack.domain.Card;
import java.util.List;
import java.util.stream.Collectors;

public class CardsScoreResponse {

    private final List<String> cards;
    private final int score;

    public CardsScoreResponse(final List<String> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public static CardsScoreResponse of(final List<Card> cards, final int score) {
        return new CardsScoreResponse(convertCards(cards), score);
    }

    private static List<String> convertCards(
            final List<Card> cards) {
        final CardConvertStrategy cardConvertStrategy = new CardConvertStrategyImpl();
        return cards.stream()
                .map(cardConvertStrategy::convert)
                .collect(Collectors.toList());
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
