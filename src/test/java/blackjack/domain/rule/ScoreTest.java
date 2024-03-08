package blackjack.domain.rule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("점수 테스트")
class ScoreTest {


    @DisplayName("점수가 파라미터를 넘는지 확인할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testScoreAbove(int value) {
        Score score = new Score(6);
        assertThat(score.isAbove(value)).isTrue();
    }

    @DisplayName("점수가 파라미터를 넘지 않는지 확인할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testScoreBelow(int value) {
        Score score = new Score(0);
        assertThat(score.isAbove(value)).isFalse();
    }

    @DisplayName("더 낮은 점수와 비교할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testScoreCompareWithBigger(int value) {
        Score score = new Score(0);
        Score target = new Score(value);
        assertThat(score.isBiggerThan(target)).isFalse();
    }

    @DisplayName("점수가 파라미터를 넘지 않는지 확인할 수 있다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testScoreCompareWithLower(int value) {
        Score score = new Score(6);
        Score target = new Score(value);
        assertThat(score.isBiggerThan(target)).isTrue();
    }
}