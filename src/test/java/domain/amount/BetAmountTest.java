package domain.amount;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.InvalidBetAmountException;
import exception.OutOfRangeBetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 0 이하일 시 예외가 발생한다.")
    @ValueSource(longs = {0, -1, -100000})
    void invalidBetAmount(long value) {
        assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(OutOfRangeBetAmount.class);
    }

    @ParameterizedTest
    @DisplayName("배팅 금액이 10으로 나누어 떨어지지 않을 시 예외가 발생한다.")
    @ValueSource(longs = {11, 53, 10001})
    void invalidUnitBetAmount(long value) {
        assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(InvalidBetAmountException.class);
    }

    @Test
    @DisplayName("배팅 금액이 1억을 초과하면 예외가 발생한다.")
    void overBetAmount() {
        assertThatThrownBy(() -> new BetAmount(100_000_010))
                .isInstanceOf(OutOfRangeBetAmount.class);
    }
}
