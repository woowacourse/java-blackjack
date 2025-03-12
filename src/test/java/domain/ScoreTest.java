package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    void Score_객체_생성_테스트() {
        // given

        // when
        Score score = new Score(21);

        // then
        Assertions.assertThat(score).isInstanceOf(Score.class);
    }
}
