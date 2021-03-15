package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {

    @Test
    @DisplayName("Score 생성 테스트")
    void testInit() {
        //when
        Score score = Score.of(13);

        //then
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
        //when
        Score addScore = Score.of(13).addScore(Score.of(7));

        //then
        assertThat(addScore).isEqualTo(Score.of(20));
    }

    @Test
    @DisplayName("점수가 21인지 확인한다.")
    void testIsBlackJack() {
        //given
        Score score1 = Score.of(13);
        Score score2 = Score.of(21);

        //when
        boolean NotTwentyOne = score1.isTwentyOne();
        boolean TwentyOne = score2.isTwentyOne();

        //then
        assertThat(NotTwentyOne).isFalse();
        assertThat(TwentyOne).isTrue();
    }

    @Test
    @DisplayName("21이 넘었는지 확인한다.")
    void testIsBust() {
        //given
        Score score1 = Score.of(21);
        Score score2 = Score.of(22);

        //when
        boolean NotBust = score1.isBust();
        boolean Bust = score2.isBust();

        //then
        assertThat(NotBust).isFalse();
        assertThat(Bust).isTrue();
    }

    @Test
    @DisplayName("비교대상보다 클 때 테스트")
    void testIsBiggerThan() {
        //given
        Score score1 = Score.of(21);
        Score score2 = Score.of(20);

        //when
        boolean biggerOne = score1.isBiggerThan(score2);

        //then
        assertThat(biggerOne).isTrue();
    }

    @Test
    @DisplayName("비교대상보다 작을 때 테스트")
    void testIsLessThan() {
        //given
        Score score1 = Score.of(20);
        Score score2 = Score.of(21);

        //when
        boolean smallerOne = score1.isLessThan(score2);

        //then
        assertThat(smallerOne).isTrue();
    }
}
