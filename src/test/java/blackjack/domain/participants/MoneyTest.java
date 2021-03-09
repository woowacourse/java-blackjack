package blackjack.domain.participants;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {

    @ParameterizedTest
    @DisplayName("배팅 금액이 0원보다 많은지 확인")
    @ValueSource(ints = {-1, 0})
    void validateNonZeroPositiveNumber(final int input) {
        Assertions.assertThatThrownBy(() -> new Money(input))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
