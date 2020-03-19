package second.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void initialize() {
        final Score score = new Score(30);

        assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    void plus() {
        final Score score = new Score(0);

        final Score ten = new Score(10);
        assertThat(score.plus(ten)).isEqualTo(new Score(10));
    }

    @Test
    @DisplayName("스코어가 21인지 체크")
    void isMaxScore() {
        final Score score = new Score(21);

        assertThat(score.isMaxScore()).isTrue();
    }
}
