package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ScoreTest {

    @Nested
    @DisplayName("inverse는")
    class Inverse {

        @ParameterizedTest
        @CsvSource(value = {"WIN|LOSE", "DRAW|DRAW", "LOSE|WIN"}, delimiter = '|')
        @DisplayName("입력한 스코어의 반대 스코어를 반환한다.")
        void returnInverseScore(Score score, Score expected) {
            Assertions.assertThat(Score.inverse(score)).isEqualTo(expected);
        }
    }
}
