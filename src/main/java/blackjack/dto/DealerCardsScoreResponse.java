package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.List;

public class DealerCardsScoreResponse {

    private final List<Card> cards;
    private final int score;

    public DealerCardsScoreResponse(List<Card> cards, int score) {
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
