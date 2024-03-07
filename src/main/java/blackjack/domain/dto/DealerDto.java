package blackjack.domain.dto;

import blackjack.domain.card.Card;
import java.util.List;

public class DealerDto {

    private final List<Card> cards;
    private final int score;

    public DealerDto(final List<Card> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
