package domain.game;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class BetTest {

    @DisplayName("1,000원 이상 1,000,000이하의 베팅 금액은 허용된다.")
    @ParameterizedTest
    @ValueSource(ints = {1_000, 99_999, 1_000_000})
    void validBet(int value) {
        assertDoesNotThrow(() -> Bet.of(value));
    }

    @DisplayName("1,000원 미만 1,000,000초과의 베팅 금액은 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 1_000_001})
    void invalidBet(int value) {
        assertThatThrownBy(() -> Bet.of(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1,000원 미만 1,000,000초과의 베팅은 할 수 없습니다.");
    }
}
