package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

class MoneyTest {
    @DisplayName("생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 100, 1000, 10000})
    void create(final int moneyValue) {
        assertThatNoException().isThrownBy(() -> new Money(moneyValue));
    }

    @DisplayName("돈을 벌다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void earn(final int value) {
        final Money money = new Money(1000);
        final Money earnMoney = new Money(value);
        final Money currentMoney = money.earn(earnMoney);

        final int expected = money.getValue() + value;
        assertThat(currentMoney.getValue()).isEqualTo(expected);
    }

    @DisplayName("지불하다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000, 100000})
    void spend(final int value) {
        final Money money = new Money(Integer.MAX_VALUE);
        final Money spendMoney = new Money(value);
        final Money currentMoney = money.spend(spendMoney);

        final int expected = money.getValue() - value;
        assertThat(currentMoney.getValue()).isEqualTo(expected);
    }

    @DisplayName("곱하다.")
    @ParameterizedTest
    @ValueSource(doubles = {1.1, 1.2, 1.5, 10.5, 100.123})
    void times(final double timesValue) {
        final Money money = new Money(10000);
        final Money currentMoney = money.times(timesValue);

        final int expected = (int) (money.getValue() * timesValue);
        assertThat(currentMoney.getValue()).isEqualTo(expected);
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
