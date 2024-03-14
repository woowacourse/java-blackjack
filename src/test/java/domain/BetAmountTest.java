package domain;

import exception.InvalidBetAmountException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 0 이하일 시 예외가 발생한다.")
    @ValueSource(ints = {0, -1, -100000})
    void invalidBetAmount(long value) {
        Assertions.assertThatThrownBy(() -> new BetAmount(value))
                .isInstanceOf(InvalidBetAmountException.class);
    }
}
