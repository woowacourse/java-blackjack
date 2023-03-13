package blackjackgame.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {
    @DisplayName("0원 이하의 금액으로 베팅하면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -5_000, -10_000})
    void betAmountLessThanMinimumValue(int amount) {
        assertThrows(IllegalArgumentException.class, () -> new Bet(amount));
    }

    @DisplayName("0원 초과의 금액으로 베팅하면 Bet 객체가 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {5_000, 15_000, 200_000})
    void betAmountGreaterThanMinimumValue(int amount) {
        assertDoesNotThrow(() -> new Bet(amount));
    }
}
