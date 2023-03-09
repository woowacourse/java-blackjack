package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("Score 캐싱을 확인한다.")
    void cachingCheck() {
        Score score = Score.of(21);

        assertThat(score == Score.of(21)).isTrue();
    }

    @Test
    @DisplayName("21 점이면 최고 점수이다.")
    void twentyOneIsMaxScore() {
        Score score = Score.of(21);

        assertThat(score.isMaxScore()).isTrue();
    }

    @Test
    @DisplayName("21 점 보다 크면 Bust 이다.")
    void LargeThenTwentyOneIsBust() {
        Score score = Score.of(22);

        assertThat(score.isBust()).isTrue();
    }

}
