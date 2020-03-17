package second.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    void 생성자테스트() {
        Score score = new Score(30);

        assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    void 점수_더하기() {
        Score score = new Score(0);

        assertThat(score.plus(10)).isEqualTo(new Score(10));
    }
}
