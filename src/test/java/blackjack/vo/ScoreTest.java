package blackjack.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @DisplayName("점수는 음수일 수 없습니다")
    @Test
    void validatePositiveValue() {
        int scoreValue = -10;

        assertThatThrownBy(() -> new Score(scoreValue))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
