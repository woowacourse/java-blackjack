package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ScoreTest {
    @Test
    @DisplayName("음수는 점수로 사용될 수 없다.")
    void negativeScoreTest() {
        int negativeValue = -1;
        assertThatThrownBy(() -> new Score(negativeValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 점수로 사용될 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"21, false", "22, true"})
    @DisplayName("점수가 21점을 초과하면 버스트")
    void burstIfExceed21Test(int value, boolean expected) {
        Score score = new Score(value);
        
        assertThat(score.isBusted()).isEqualTo(expected);
    }
}
