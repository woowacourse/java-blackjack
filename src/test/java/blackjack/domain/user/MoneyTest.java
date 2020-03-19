package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
    @Test
    @DisplayName("Money 생성하고 값 불러오기 테스트")
    void moneyGenerateTest() {
        assertThat(new Money(10000).getMoney()).isEqualTo(10000);
    }

    @ParameterizedTest
    @CsvSource(value= {"5000,1,5000", "10000,1.5,15000", "30000,-1,-30000", "50000,0,0"})
    @DisplayName("Money 곱하기 테스트")
    void moneyMultiplyTest(int moneyAmount, double rate, int expected) {
        Money money = new Money(moneyAmount);
        assertThat(money.multiply(rate)).isEqualTo(new Money(expected));
    }

    @ParameterizedTest
    @CsvSource(value= {"0,5000,5000", "100000,-100000,0", "30000,45000,75000"})
    @DisplayName("Money 더하기 테스트")
    void moneyAddTest(int moneyAmount, int addedMoney, int expected) {
        Money money = new Money(moneyAmount);
        assertThat(money.add(new Money(addedMoney))).isEqualTo(new Money(expected));
    }

    @ParameterizedTest
    @CsvSource(value= {"0,5000,-5000", "100000,-100000,200000", "30000,30000,0"})
    @DisplayName("Money 빼기 테스트")
    void moneySubtractTest(int moneyAmount, int subtractedMoney, int expected) {
        Money money = new Money(moneyAmount);
        assertThat(money.subtract(new Money(subtractedMoney))).isEqualTo(new Money(expected));
    }
}
