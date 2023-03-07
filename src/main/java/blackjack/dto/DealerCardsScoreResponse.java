package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerCardsScoreResponse {

    private final List<Card> cards;
    private final int score;

    public DealerCardsScoreResponse(List<Card> cards, int score) {
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        return score;
    }
}
