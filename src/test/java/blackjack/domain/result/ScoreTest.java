package blackjack.domain.result;

import blackjack.domain.result.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScoreTest {
    @Test
    @DisplayName("Bust 점수 확인")
    void checkBust() {
        Score bustScore = new Score(22);
        assertThat(bustScore.isNotOver(21)).isFalse();
    }
}
