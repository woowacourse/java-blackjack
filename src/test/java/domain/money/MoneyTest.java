package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @DisplayName("정수형으로 돈을 반환한다.")
    @ParameterizedTest
    @ValueSource(ints = {999, 0, -1000, 1000})
    void getValue(int value) {
        Money money = Money.valueOf(value);
        assertThat(money.getValue()).isEqualTo(value);
    }

    @DisplayName("수익률을 곱한다.")
    @Test
    void multiply() {
        Money money = Money.valueOf(1000);
        assertThat(money.multiply(1.5)).isEqualTo(1500);
    }
}
