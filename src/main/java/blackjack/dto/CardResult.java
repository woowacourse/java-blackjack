package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;

public class CardResult {

    private final List<Card> cards;
    private final int score;

    public CardResult(List<Card> cards, int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        return score;
    }
}
