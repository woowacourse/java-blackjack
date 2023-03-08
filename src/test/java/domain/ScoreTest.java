package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ScoreTest {

    @Test
    @DisplayName("Score 를 생성한다.")
    void test_create() {
        Assertions.assertDoesNotThrow(() -> new Score(1));
    }

    @ParameterizedTest(name = "현재 점수에 따라 A가 11 또는 1이 된다")
    @CsvSource(value = {"1,11", "11,21"})
    void test_addScoreByAce(int value, int result) {
        Score score = new Score(value);

        assertThat(score.addScoreByAce()).isEqualTo(new Score(result));
    }
}