package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("점수의 최솟값은 0점 이다")
    void min_score() {
        assertThat(Score.min().getValue()).isZero();
    }

    @ParameterizedTest
    @CsvSource(value = {"21,false", "22,true"})
    @DisplayName("최고 점수(21점)을 넘으면 bust인지 확인해주는 기능 테스트")
    void check_bust(int value, boolean expected) {
        Score score = new Score(value);

        assertThat(score.isBust()).isEqualTo(expected);
    }

    @Test
    @DisplayName("점수 덧셈 테스트")
    void add() {
        Score score = new Score(10);
        Score addedScore = score.plus(score);

        assertThat(addedScore.getValue()).isEqualTo(20);
    }
}