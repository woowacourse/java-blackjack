package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {
    @DisplayName("생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 100, 1000, 10000})
    void create(final int moneyValue) {
        assertThatNoException().isThrownBy(() -> new Money(moneyValue));
    }

    @DisplayName("생성시 입력값이 음수일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100, -1000, -10000})
    void throwExceptionWhenValueIsNegative(final int moneyValue) {
        assertThatThrownBy(() -> new Money(moneyValue))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("돈을 번 금액이 음수일 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -100, -1000, -10000})
    void throwExceptionWhenEarnValueIsNegative(final int value) {
        final Money money = new Money(1000);
        assertThatThrownBy(() -> money.earn(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("돈을 벌다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void earn(final int value) {
        final Money money = new Money(1000);
        assertThatNoException().isThrownBy(() -> money.earn(value));
    }

    @DisplayName("사용할 금액이 보유한 금액을 넘어설 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void throwExceptionWhenSpendValueIsGreaterThanCurrentValue(final int value) {
        final Money money = new Money(0);
        assertThatThrownBy(() -> money.spend(value))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불하다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void spend(final int value) {
        final Money money = new Money(Integer.MAX_VALUE);
        assertThatNoException().isThrownBy(() -> money.spend(value));
    }

    @DisplayName("현금을 확인한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void getValue(final int value) {
        final Money money = new Money(value);
        final int currentValue = money.getValue();

        assertThat(currentValue).isEqualTo(value);
    }
}
