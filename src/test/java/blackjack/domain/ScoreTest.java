package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("음수는 점수로 사용될 수 없다.")
    void negativeScoreTest() {
        int negativeValue = -1;
        assertThatThrownBy(() -> new Score(negativeValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음수는 점수로 사용될 수 없습니다.");
    }

}
