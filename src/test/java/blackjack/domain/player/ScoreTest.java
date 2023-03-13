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

    @ParameterizedTest
    @CsvSource(value = {"20,false", "21,true", "22,false"})
    @DisplayName("21점이면 블랙잭인지 확인해주는 기능 테스트")
    void check_blackjack(int value, boolean expected) {
        Score score = new Score(value);

        assertThat(score.isBlackjack()).isEqualTo(expected);
    }

    @Test
    @DisplayName("점수 덧셈 테스트")
    void add() {
        Score score = new Score(10);
        Score addedScore = score.plus(score);

        assertThat(addedScore.getValue()).isEqualTo(20);
    }

    @ParameterizedTest
    @CsvSource(value = {"20,true", "15,false"})
    @DisplayName("같은 점수인지 확인해주는 기능 테스트")
    void is_same_score(int point, boolean expected) {
        Score score = new Score(20);
        Score targetScore = new Score(point);

        assertThat(score.isSameScore(targetScore)).isEqualTo(expected);
    }
}