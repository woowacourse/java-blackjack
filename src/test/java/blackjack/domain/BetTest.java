package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.Bet.*;
import static org.assertj.core.api.Assertions.assertThatCode;

class BetTest {

    @DisplayName("최소 금액 이상만 허용한다.")
    @Test
    void validateRangeSuccess() {
        assertThatCode(() -> new Bet(MIN_BET))
                .doesNotThrowAnyException();
    }

    @DisplayName("최소 금액보다 작은 경우 예외가 발생한다.")
    @Test
    void validateRangeFail() {
        assertThatCode(() -> new Bet(MIN_BET - 1))
                .hasMessage(BET_RANGE_MESSAGE)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("금액의 단위를 만족해야 한다.")
    @Test
    void validateUnit() {
        assertThatCode(() -> new Bet(BET_UNIT))
                .doesNotThrowAnyException();
    }
}
