package blackjack.domain.dto;

import java.util.List;

public class HitResultDto {

    private final List<CardDto> cards;
    private final int score;

    public HitResultDto(List<CardDto> cards, int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<CardDto> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
