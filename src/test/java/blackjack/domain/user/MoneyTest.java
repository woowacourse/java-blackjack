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
        assertThat(money.multiply(rate).getMoney()).isEqualTo(expected);
    }
}
