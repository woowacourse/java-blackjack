package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @ParameterizedTest(name = "입력값 : {1}")
    @CsvSource({"22,True", "21,False", "17,False"})
    void 버스트_발생(int scoreValue, boolean expect) {
        assertThat(new Score(scoreValue).isBust()).isEqualTo(expect);
    }

    @Test
    @DisplayName("Score 비교 테스트")
    void compare_score() {
        Score score1 = new Score(7);
        Score score2 = new Score(8);
        assertThat(score1.lessThan(score2)).isTrue();
    }
}
