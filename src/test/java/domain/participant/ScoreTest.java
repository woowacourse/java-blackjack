package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ScoreTest {

    @Test
    void 점수_객체를_생성한다() {
        // given
        int value = 21;
        Score score = Score.from(21);

        // when & then
        assertThat(score.toInt()).isEqualTo(value);
    }

    @Test
    void 점수가_0인_객체를_생성한다() {
        //given
        Score zero = Score.zero();

        // when & then
        Score expected = Score.from(0);
        assertThat(zero).isEqualTo(expected);
    }

    @Test
    void 비교_점수보다_작거나_같으면_true를_반환한다() {
        // given
        Score score = Score.from(17);
        Score other = Score.from(17);

        // when & then
        assertThat(score.isLessThanEqual(other));
    }

    @Test
    void 비교_점수보다_작으면_true를_반환한다() {
        // given
        Score score = Score.from(16);
        Score other = Score.from(17);

        // when & then
        assertThat(score.isLessThan(other));
    }

    @Test
    void 비교_점수보다_크면_true를_반환한다() {
        // given
        Score score = Score.from(21);
        Score other = Score.from(17);

        // when & then
        assertThat(score.isGreaterThan(other));
    }

    @Test
    void 점수를_int형으로_반환한다() {
        // given
        Score score = Score.from(21);

        // when
        int value = score.toInt();

        // then
        assertThat(value).isEqualTo(21);
    }

    @Test
    void 점수가_감소한다() {
        // given
        Score score = Score.from(21);
        Score minusScore = Score.from(15);

        // when
        Score result = score.minus(minusScore);

        // then
        Score expected = Score.from(6);
        assertThat(result).isEqualTo(expected);
    }
}
