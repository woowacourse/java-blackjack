package blackjack.dto.view;

import blackjack.domain.result.Score;
import blackjack.domain.card.Card;

import java.util.List;

public class CardAndScoreResult {

    private final String name;
    private final List<Card> cards;
    private final int scoreValue;

    public CardAndScoreResult(final String name, final List<Card> cards, final Score score) {
        this.name = name;
        this.cards = List.copyOf(cards);
        this.scoreValue = score.getValue();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
