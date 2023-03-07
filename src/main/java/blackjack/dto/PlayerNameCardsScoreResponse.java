package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.List;

public class PlayerNameCardsScoreResponse {

    private final String name;
    private final List<Card> cards;
    private final int score;

    public PlayerNameCardsScoreResponse(String name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
