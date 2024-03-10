package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("다른 스코어보다 큰지 확인한다.")
    @Test
    void isBiggerThan() {
        Score score1 = new Score(21);
        Score score2 = new Score(20);

        boolean isBigger = score1.isBiggerThan(score2);

        assertThat(isBigger).isTrue();
    }

    @DisplayName("다른 스코어보다 크지 않은지 확인한다.")
    @Test
    void isNotBiggerThan() {
        Score score1 = new Score(21);
        Score score2 = new Score(22);

        boolean isBigger = score1.isBiggerThan(score2);

        assertThat(isBigger).isFalse();
    }

    @DisplayName("숫자가 1이하이면 예외를 발생한다.")
    @Test
    void from() {
        assertThatIllegalStateException()
                .isThrownBy(() -> new Score(1))
                .withMessage("현재 갖고있는 카드의 합이 정상적이지 않습니다.");
    }
}
