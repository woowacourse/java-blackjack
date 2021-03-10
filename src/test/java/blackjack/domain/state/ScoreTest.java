package blackjack.domain.state;

import blackjack.state.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreTest {

    @DisplayName("스코어 점수 반환 확인")
    @Test
    void scoreTest() {
        Score score = new Score(10);

        assertTrue(score.isChangeAceNumber());
    }

    @DisplayName("10점 추가 기능 확인")
    @Test
    void plusTen() {
        Score score = new Score(10);

        assertThat(score.aceNumberChange()).isEqualTo(20);
    }
}
