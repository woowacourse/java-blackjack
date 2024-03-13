package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("금액은 0 또는 음수가 될 수 없다")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    void validateTest_whenValueIsNotPositive_throwException(int value) {

        assertThatThrownBy(() -> new Money(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 양수이어야 합니다.");
    }
}
