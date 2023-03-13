package blackjack.dto;

import blackjack.domain.user.Score;

public class ScoreDto {

    private final int value;

    public ScoreDto(Score score) {
        this.value = score.getValue();
    }

    public int getValue() {
        return value;
    }
}
