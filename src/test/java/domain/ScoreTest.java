package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @Test
    @DisplayName("점수를 더할 수 있다")
    void test_add_score() {
        var score = new Score(3);
        var score2 = new Score(4);

        assertThat(score.add(score2)).isEqualTo(new Score(7));
    }

    @ParameterizedTest(name = "점수가 Bust인지 알 수 있다")
    @CsvSource({"21,false", "22,true"})
    void test_is_bust(int providedScore, boolean isBust) {
        var score = new Score(providedScore);

        assertThat(score.isBust()).isEqualTo(isBust);
    }

    @ParameterizedTest(name = "보너스 점수를 더할 수 있는지 알 수 있다")
    @CsvSource({"11,true", "12,false"})
    void test_can_add_ten(int providedScore, boolean canAddBonus) {
        var score = new Score(providedScore);

        assertThat(score.canAddBonus()).isEqualTo(canAddBonus);
    }

    @ParameterizedTest(name = "다른 점수보다 큰지 알 수 있다")
    @CsvSource({"11,false", "12,true"})
    void test_is_greater_than(int providedScore, boolean isGreater) {
        var score = new Score(providedScore);
        var other = new Score(11);

        assertThat(score.isGreaterThan(other)).isEqualTo(isGreater);
    }

    @ParameterizedTest(name = "다른 점수와 동일한지 알 수 있다")
    @CsvSource({"11,true", "12,false"})
    void test_is_same_with(int providedScore, boolean isGreater) {
        var score = new Score(providedScore);
        var other = new Score(11);

        assertThat(score.isSameWith(other)).isEqualTo(isGreater);
    }

    @ParameterizedTest(name = "다른 점수보다 작은지 알 수 있다")
    @CsvSource({"10,true", "11,false"})
    void test_is_less_than(int providedScore, boolean isLess) {
        var score = new Score(providedScore);
        var other = new Score(11);

        assertThat(score.isLessThan(other)).isEqualTo(isLess);
    }
}
