package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    @DisplayName("money를 생성한다.")
    void test_create() {
        assertDoesNotThrow(() -> new Money(1_000));
    }

    @Test
    @DisplayName("money는 100,000 초과 값으로 생성하면 예외가 발생한다.")
    void test_create_fail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Money(100_001));
    }

    @Test
    @DisplayName("amount가 0인 money를 생성한다.")
    void test_zero() {
        assertThat(Money.zero())
                .isEqualTo(new Money(0));
    }

    @ParameterizedTest(name = "두 money의 합을 구한다.")
    @CsvSource({"1_000,2_000,3_000", "2_000,4_000,6_000"})
    void test_add(int number1, int number2, int result) {
        Money money = new Money(number1);

        assertThat(money.add(new Money(number2)))
                .isEqualTo(new Money(result));
    }

    @ParameterizedTest(name = "두 money의 차를 구한다.")
    @CsvSource({"1_000,2_000,-1_000", "4_000,2_000,2_000"})
    void test_subtract(int number1, int number2, int result) {
        Money money = new Money(number1);

        assertThat(money.subtract(new Money(number2)))
                .isEqualTo(new Money(result));
    }

    @ParameterizedTest(name = "money에 배수를 곱한다.")
    @CsvSource({"1000, 1.5", "2000, -1", "3000, 1"})
    void test_multiply(int number, double times) {
        Money money = new Money(number);

        assertThat(money.multiply(times))
                .isEqualTo(new Money((int) (number * times)));
    }
}