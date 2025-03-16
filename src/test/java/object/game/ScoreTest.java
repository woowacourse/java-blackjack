package object.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScoreTest {
    @Test
    void Score_객체_생성_테스트() {
        // given

        // when
        Score score = Score.of(21, 2);

        // then
        Assertions.assertThat(score).isInstanceOf(Score.class);
    }

    @Test
    void 블랙잭_확인_테스트() {
        // given
        Score score = Score.of(21, 2);

        // when
        boolean actual = score.isBlackJack();

        // then
        boolean expected = true;
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 버스트_확인_테스트() {
        // given
        Score score = Score.of(22, 2);

        // when
        boolean actual = score.isBust();

        // then
        boolean expected = true;
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
