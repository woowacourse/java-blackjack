package blackjack.dto.domain;

import blackjack.domain.card.Card;
import blackjack.domain.result.Score;

import java.util.List;

public class CardAndScore {

    private final List<Card> cards;
    private final Score score;

    public CardAndScore(final List<Card> cards, final Score score) {
        this.cards = List.copyOf(cards);
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Score getScore() {
        return score;
    }
}
