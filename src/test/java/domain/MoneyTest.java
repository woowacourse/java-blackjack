package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MoneyTest {

    @Test
    @DisplayName("금액은 음수로 시작할 수 없다")
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

    @ParameterizedTest(name = "배당을 지급할 수 있다")
    @CsvSource({"1,2468", "1.5,3085"})
    void test_distribute_dividend(double dividend, int expectedValue) {
        var money = Money.of(1234);

        assertThat(money.distribute(dividend)).isEqualTo(Money.of(expectedValue));
    }

    @ParameterizedTest(name = "1원 이하는 절삭한다")
    @CsvSource({"1.7,3331", "1.1,2591"})
    void test_remove_points_under_unit(double dividend, int expectedValue) {
        var money = Money.of(1234);

        assertThat(money.distribute(dividend)).isEqualTo(Money.of(expectedValue));
    }
}
