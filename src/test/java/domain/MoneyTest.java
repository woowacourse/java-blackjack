package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {
    @DisplayName("음수는 베팅 금액으로 설정할 수 없다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void notNegativeValue(final int value) {
        // given

        // when & then
        assertThatThrownBy(() -> {
            new Money(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
