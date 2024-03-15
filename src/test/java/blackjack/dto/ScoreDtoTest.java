package blackjack.dto;

import blackjack.domain.card.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreDtoTest {

    @Test
    void dto로_변환할_수_있다() {
        final Score score = Score.valueOf(10);
        final ScoreDto scoreDto = ScoreDto.from(score);

        assertThat(scoreDto.score()).isEqualTo(10);
    }
}
