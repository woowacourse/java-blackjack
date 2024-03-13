package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("점수를 더할 수 있다.")
    void addScoreTest() {
        Score score = new Score(2);

        assertThat(score.add(3)).isEqualTo(new Score(5));
    }
}
