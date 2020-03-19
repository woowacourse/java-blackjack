package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.exceptions.InvalidMoneyException;

public class MoneyTest {
    @DisplayName("Money 생성자 테스트")
    @Test
    void validConstructorTest() {
        // given
        int amount = 10000;
        // when
        Money money = new Money(amount);
        Money moneyFromString = new Money(String.valueOf(amount));
        // then
        assertThat(money.getAmount()).isEqualTo(amount);
        assertThat(moneyFromString.getAmount()).isEqualTo(amount);
    }

    @DisplayName("Money 생성자 실패 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"string", "1.5"})
    public void invalidConstructorTest(String value) {
        assertThatThrownBy(() -> {
            new Money(value);
        }).isInstanceOf(InvalidMoneyException.class);
    }

    @DisplayName("Money 덧셈 테스트")
    @Test
    public void addTest() {
        Money money = new Money(1000L);
        Money other = new Money(3000L);
        long expected = 4000L;
        assertThat(money.add(other).getAmount()).isEqualTo(expected);
    }

    @DisplayName("Money 뺄셈 테스트")
    @Test
    public void subtractTest() {
        Money money = new Money(1000L);
        Money other = new Money(3000L);
        long expected = -2000L;
        assertThat(money.subtract(other).getAmount()).isEqualTo(expected);
    }

    @DisplayName("Money 곱셈 테스트")
    @Test
    void multiplyTest() {
        Money money = new Money(10L);
        double multiplyValue = 1.5;
        long expected = 15L;
        assertThat(money.multiply(multiplyValue).getAmount()).isEqualTo(expected);
    }
}
