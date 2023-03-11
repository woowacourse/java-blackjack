package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.vo.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("score을 생성한다")
    void createScoreTest() {
        Score score = Score.of(10);

        assertThat(score.getValue()).isEqualTo(10);
    }
}
