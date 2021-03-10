package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("Score 생성 테스트")
    void testInit() {
        Score score = Score.of(13);
        assertThat(score).isEqualTo(Score.of(13));
    }

    @Test
    @DisplayName("유효하지 않은 Score 생성 테스트")
    void testInvalidScoreValue() {
        assertThatThrownBy(() -> Score.of(-1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Score 더하기 테스트")
    void testAddScore() {
        assertThat(Score.of(13).addScore(Score.of(7)))
                .isEqualTo(Score.of(20));
    }

    @Test
    @DisplayName("점수가 21인지 확인한다.")
    void testIsBlackJack() {
        Score score = Score.of(13);
        assertThat(score.isTwentyOne()).isFalse();
        score = Score.of(21);
        assertThat(score.isTwentyOne()).isTrue();
    }

    @Test
    @DisplayName("21이 넘었는지 확인한다.")
    void testIsBust() {
        Score score = Score.of(21);
        assertThat(score.isBust()).isFalse();
        score = Score.of(22);
        assertThat(score.isBust()).isTrue();
    }

    @Test
    @DisplayName("비교대상보다 클 때 테스트")
    void testIsBiggerThan() {
        assertThat(Score.of(21).isBiggerThan(Score.of(20))).isTrue();
    }

    @Test
    @DisplayName("비교대상보다 작을 때 테스트")
    void testIsLessThan() {
        assertThat(Score.of(20).isLessThan(Score.of(21))).isTrue();
    }
}
