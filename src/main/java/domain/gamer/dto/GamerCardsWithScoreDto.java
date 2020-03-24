package domain.gamer.dto;

import domain.card.Card;

import java.util.List;

public class GamerCardsWithScoreDto extends GamerCardsDto {
    private int score;

    public GamerCardsWithScoreDto(String name, List<Card> cards, int score) {
        super(name, cards);
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
