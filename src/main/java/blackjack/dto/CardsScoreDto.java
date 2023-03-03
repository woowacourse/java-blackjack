package blackjack.dto;

import blackjack.domain.Card;

import java.util.List;

public class CardsScoreDto {

    private final List<Card> cards;
    private final int score;


    public CardsScoreDto(final List<Card> cards, final int score) {
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
