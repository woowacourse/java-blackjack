package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BlackjackStatusTest {
    @DisplayName("숫자가 1이하이면 예외를 발생한다.")
    @Test
    void from() {
        assertThatIllegalStateException()
                .isThrownBy(() -> BlackjackStatus.from(new Score(1)))
                .withMessage("현재 갖고있는 카드의 합이 정상적이지 않습니다.");
    }

    @DisplayName("숫자에 따른 블랙잭 상태를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"20, ALIVE", "21, BLACKJACK", "22, DEAD"})
    void from(int sum, BlackjackStatus expected) {
        // given & when
        BlackjackStatus blackjackStatus = BlackjackStatus.from(new Score(sum));

        // then
        assertThat(blackjackStatus).isEqualTo(expected);
    }
}
