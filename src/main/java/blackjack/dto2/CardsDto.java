package blackjack.dto2;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class CardsDto {

    private final List<Card> cards;
    private final int scoreValue;

    public CardsDto(final List<Card> cards, final Score score) {
        this.cards = Collections.unmodifiableList(cards);
        this.scoreValue = score.toInt();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    @Override
    public String toString() {
        return "CardBundleDto{" +
                "cards=" + cards +
                ", scoreValue=" + scoreValue +
                '}';
    }
}
