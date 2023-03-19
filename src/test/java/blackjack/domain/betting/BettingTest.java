package blackjack.domain.betting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BettingTest {

    @Test
    @DisplayName("베팅 금액을 제대로 반환되는지 확인한다")
    void getBettingTest() {
        final Betting betting = Betting.from("1000");

        assertEquals(1000, betting.getValue());
    }

    @Test
    @DisplayName("베팅 금액이 숫자가 아니라면 예외 발생")
    void throwExceptionWhenNotNumber() {
        assertThatThrownBy(() -> Betting.from("test"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 숫자만 가능합니다.");
    }

    @Test
    @DisplayName("베팅 금액이 범위 이하일 때 예외 발생")
    void throwExceptionWhenLessMoney() {
        assertThrows(IllegalArgumentException.class, () -> Betting.from("999"));
    }

    @Test
    @DisplayName("베팅 금액이 범위 초과할 때 예외 발생")
    void throwExceptionWhenExceedMoney() {
        assertThrows(IllegalArgumentException.class, () -> Betting.from("1000001"));
    }
}
