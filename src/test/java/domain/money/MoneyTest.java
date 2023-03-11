package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
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
}
