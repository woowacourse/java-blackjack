package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
    @Test
    void 클래스_생성_테스트() {
        Money money = new Money(3000);
        Assertions.assertThat(money).hasFieldOrPropertyWithValue("money",3000);
    }

    @ParameterizedTest
    @ValueSource(ints = {10,400,21000})
    void 금액_반환_테스트(int input) {
        Money money = new Money(input);
        Assertions.assertThat(money.getValue()).isEqualTo(input);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,2,20","1000,0.5,500","3,0.5,1"})
    void 곱하기_연산_테스트(int number, double mul, int expected) {
        Money money = new Money(number);
        Assertions.assertThat(money.multiply(mul).getValue()).isEqualTo(expected);
    }
}
