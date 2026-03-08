package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    @Test
    @DisplayName("21이 넘으면 버스트 처리한다.")
    void isBurstTest() {
        // given
        Score score = new Score(22);
        // when & then
        assertThat(score.isBurst()).isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 버스트 처리하지 않는다.")
    void isBurstTest2() {
        // given
        Score score = new Score(21);
        // when & then
        assertThat(score.isBurst()).isFalse();
    }
}