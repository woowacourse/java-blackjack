package blackjack.dto;

import blackjack.domain.card.Score;

public record ScoreDto(int score) {

    public static ScoreDto from(final Score score) {
        return new ScoreDto(score.getScore());
    }
}
