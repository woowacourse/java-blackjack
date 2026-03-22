package domain.participant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingTest {
    @Test
    @DisplayName("베팅 금액이 음수면 예외가 발생한다")
    void throwExceptionWhenBettingAmountIsNegative() {
        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Betting(-1000)
        );

        assertEquals("베팅 금액은 10 이상이며 10원 단위여야 합니다.", exception.getMessage());
    }

    @Test
    @DisplayName("베팅 금액이 10원 단위가 아니면 예외가 발생한다")
    void throwExceptionWhenBettingAmountIsNotMultipleOfTen() {
        final IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Betting(1005)
        );

        assertEquals("베팅 금액은 10 이상이며 10원 단위여야 합니다.", exception.getMessage());
    }
}
