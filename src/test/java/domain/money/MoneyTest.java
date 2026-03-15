package domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("양수 금액으로 Money를 생성한다")
    void createPositiveMoney() {
        // given
        int amount =  1000;

        // when
        Money money = Money.of(amount);

        // then
        assertEquals(new BigDecimal(amount), money.getAmount());
    }

    @Test
    @DisplayName("0 금액으로 Money를 생성할 수 있다")
    void createZeroMoney() {
        // given
        Money money = Money.zero();

        // when - then
        assertEquals(new BigDecimal("0"), money.getAmount());
    }

    @Test
    @DisplayName("음수 금액으로 Money를 생성할 수 있다")
    void createNegativeMoney() {
        // given
        int amount = -500;

        // when
        Money money = Money.of(-500);

        // then
        assertEquals(new BigDecimal(-500), money.getAmount());
    }

    @Test
    @DisplayName("Money를 더할 수 있다")
    void addMoney() {
        // given
        Money money1 = Money.of(1000);
        Money money2 = Money.of(2000);

        // when - then
        assertEquals(Money.of(3000), money1.add(money2));
    }

    @Test
    @DisplayName("Money에 음수를 더할 수 있다")
    void addNegativeMoney() {
        // given
        Money money1 = Money.of(3000);
        Money money2 = Money.of(-1000);

        // when - then
        assertEquals(Money.of(2000), money1.add(money2));
    }

    @Test
    @DisplayName("Money에 0을 더할 수 있다")
    void addZeroMoney() {
        // given
        Money money = Money.of(2000);

        // when - then
        assertEquals(Money.of(2000), money.add(Money.zero()));
    }

    @Test
    @DisplayName("Money에 배율을 곱할 수 있다")
    void multiplyMoney() {
        // given
        Money money = Money.of(2000);

        // when - then
        assertEquals(Money.of(4000), money.multiply(new BigDecimal("2")));
    }

    @Test
    @DisplayName("Money에 소수 배율을 곱할 수 있다")
    void multiplyDecimalRatio() {
        // given
        Money money = Money.of(3000);

        // when - then
        assertEquals(Money.of(4500), money.multiply(new BigDecimal("1.5")));
    }

    @Test
    @DisplayName("Money에 0을 곱하면 0이 된다")
    void multiplyZero() {
        // given
        Money money = Money.of(5000);

        // when - then
        assertEquals(Money.zero(), money.multiply(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("Money에 음수를 곱할 수 있다")
    void multiplyNegative() {
        // given
        Money money = Money.of(3000);

        // when - then
        assertEquals(Money.of(-3000), money.multiply(new BigDecimal("-1")));
    }

    @Test
    @DisplayName("Money를 negate 하면 부호가 반전된다")
    void negateMoney() {
        // given
        Money money = Money.of(3000);

        // when - then
        assertEquals(Money.of(-3000), money.negate());
    }
}
