package domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("Score 생성 테스트")
    @Test
    void createTest() {
        Score score = new Score(10);
        assertThat(score.getValue()).isEqualTo(10);
    }
}
