package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class ScoreTest {
    @Test
    void 점수가_21보다_클_경우_Bust다() {
        int score = 22;

        Score result = Score.get(score);

        assertThat(result.isBustScore()).isTrue();
    }

    @Test
    void 점수가_21일_경우_Blackjack이다() {
        int score = 21;

        Score result = Score.get(score);

        assertThat(result.isBlackjackScore()).isTrue();
    }

    @Test
    void 점수가_21보다_작을_경우_Blackjack이_아니다() {
        int score = 20;

        Score result = Score.get(score);

        assertThat(result.isBlackjackScore()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 42})
    void 점수가_범위를_벗어날_경우_예외가_발생한다(int score) {
        assertThatThrownBy(() -> Score.get(score))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 두_점수를_더한다() {
        Score first = Score.get(10);
        Score second = Score.get(11);

        Score result = first.plus(second);

        assertThat(result).isEqualTo(Score.get(21));
    }

    @Test
    void 보너스_점수를_더했을_때_Bust가_아니면_보너스_점수를_더한다() {
        Score score = Score.get(1);

        Score result = score.plusBonusScore();

        assertThat(result).isEqualTo(Score.get(11));
    }

    @Test
    void 보너스_점수를_더했을_때_Bust이면_보너스_점수를_더하지_않는다() {
        Score first = Score.get(20);

        Score result = first.plusBonusScore();

        assertThat(result).isEqualTo(Score.get(20));
    }

    @ParameterizedTest
    @CsvSource(value = {"19:true", "21:false"}, delimiter = ':')
    void 현재_점수가_다른_점수보다_큰지_확인한다(int otherValue, boolean expected) {
        Score score = Score.get(20);
        Score other = Score.get(otherValue);

        assertThat(score.isGreaterThan(other)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"19:false", "21:true"}, delimiter = ':')
    void 현재_점수가_다른_점수보다_작은지_확인한다(int otherValue, boolean expected) {
        Score score = Score.get(20);
        Score other = Score.get(otherValue);

        assertThat(score.isLessThan(other)).isEqualTo(expected);
    }
}
