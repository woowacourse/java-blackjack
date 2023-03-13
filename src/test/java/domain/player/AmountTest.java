package domain.player;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AmountTest {
    @DisplayName("베팅 가능 금액은 1 이상 1억 이하이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 99_999_999, 100_000_000})
    void validBattingTest(final int amount) {
        assertDoesNotThrow(() -> new Amount(amount));
    }

    @DisplayName("1 미만 혹은 1억을 초과하여 베팅하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 100_000_001, 100_000_002})
    void invalidBattingTest(final int amount) {
        assertThrows(IllegalArgumentException.class, () -> new Amount(amount));
    }
}
