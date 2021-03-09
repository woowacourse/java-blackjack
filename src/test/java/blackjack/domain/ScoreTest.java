package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("동일한 점수면 동일 스코어로 본다.")
    @Test
    void create() {
        Score score = new Score(3);
        assertThat(score).isEqualTo(new Score(3));
    }

}