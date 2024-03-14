package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import blackjack.domain.rule.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @DisplayName("숫자가 1이하이면 예외를 발생한다.")
    @Test
    void from() {
        assertThatIllegalStateException()
                .isThrownBy(() -> new Score(1))
                .withMessage("현재 갖고있는 카드의 합이 정상적이지 않습니다.");
    }
}
