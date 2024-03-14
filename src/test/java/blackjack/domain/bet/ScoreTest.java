package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.rule.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("점수를 더한다.")
    @Test
    void plus() {
        Score one = Score.from(1);
        Score two = Score.from(2);

        final Score result = one.plus(two);

        assertThat(result).isEqualTo(Score.from(3));
    }

    @DisplayName("점수를 뺀다.")
    @Test
    void minus() {
        Score one = Score.from(1);
        Score two = Score.from(2);

        final Score result = two.minus(one);

        assertThat(result).isEqualTo(Score.from(1));
    }

    @DisplayName("점수는 음수일 수 없다.")
    @Test
    void validateRange() {
        assertThatThrownBy(() -> Score.from(-1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("점수가 음수일 수 없습니다.");
    }
}
