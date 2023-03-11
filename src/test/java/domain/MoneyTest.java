package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {

    @Test
    @DisplayName("원금은 음수로 생성할 수 없다")
    void test_money_is_not_negative() {
        assertThatThrownBy(() -> Money.of(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액끼리 더할 수 있다")
    void test_add_money() {
        var money = Money.of(12);
        var other = Money.of(1234);

        assertThat(money.add(other)).isEqualTo(Money.of(1246));
    }

    @Test
    @DisplayName("금액끼리 뺄 수 있다")
    void test_sub_money() {
        var money = Money.of(500);
        var other = Money.of(500);

        assertThat(money.sub(other)).isEqualTo(Money.of(0));
    }

    @ParameterizedTest(name = "곱할 수 있다")
    @CsvSource({"1,1234", "1.5,1851"})
    void test_distribute_dividend(double dividend, int expectedValue) {
        var money = Money.of(1234);

        assertThat(money.multiply(dividend)).isEqualTo(Money.of(expectedValue));
    }

    @ParameterizedTest(name = "1원 이하는 절삭한다")
    @CsvSource({"1.7,2097", "1.1,1357"})
    void test_remove_points_under_unit(double dividend, int expectedValue) {
        var money = Money.of(1234);

        assertThat(money.multiply(dividend)).isEqualTo(Money.of(expectedValue));
    }

    @DisplayName("다른 금액보다 작은지 알 수 있다")
    @ParameterizedTest(name = "{0} < {1}? {2}")
    @CsvSource({"1233,1234,true", "1234,1234,false"})
    void test_less_than_other(int value, int otherValue, boolean isLess) {
        var money = Money.of(value);
        var other = Money.of(otherValue);

        assertThat(money.isLessThan(other)).isEqualTo(isLess);
    }

    @Test
    @DisplayName("원금 대비 수익을 알 수 있다.")
    void test_calculateProfit() {
        var money = Money.of(1234);
        money = money.add(money.multiply(1.2));
        money = money.add(Money.of(750));

        assertThat(money.getProfit()).isEqualTo(2230);
    }
}
