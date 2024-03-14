package domain;

import exception.InvalidBetAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 0 이하일 시 예외가 발생한다.")
    @ValueSource(longs = {0, -1, -100000})
    void invalidBetAmount(long value) {
        Assertions.assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(InvalidBetAmountException.class);
    }

    @ParameterizedTest
    @DisplayName("배팅 금액이 10으로 나누어 떨어질 시 예외가 발생한다.")
    @ValueSource(longs = {5, 13, 10001})
    void invalidUnitBetAmount(long value) {
        Assertions.assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(InvalidBetAmountException.class);
    }
}
