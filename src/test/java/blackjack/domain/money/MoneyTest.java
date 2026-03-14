package blackjack.domain.money;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void 두_금액을_더하면_합산된_금액을_반환한다() {
        Money money = new Money(10000);
        Money other = new Money(5000);

        assertThat(money.add(other)).isEqualTo(new Money(15000));
    }

    @Test
    void 배수의_퍼센트_만큼_곱셈이_가능하다() {
        Money money = new Money(10000);

        assertThat(money.multiply(1.5)).isEqualTo(new Money(15000));
    }

    @ParameterizedTest(name = "금액 {0}원에 {1}배 시 {2}원으로 반올림된다")
    @CsvSource({
            "10000, 1.5, 15000",
            "10001, 1.5, 15002",
            "3, 1.5, 5",
            "1, 1.5, 2",
    })
    void 소수점_곱셈_결과는_반올림하여_반환한다(int value, double multiplier, int expected) {
        assertThat(new Money(value).multiply(multiplier)).isEqualTo(new Money(expected));
    }

    @Test
    void 금액을_부정하면_부호가_반전된_금액을_반환한다() {
        Money money = new Money(10000);

        assertThat(money.negate()).isEqualTo(new Money(-10000));
    }

    @Test
    void 음수_금액을_부정하면_양수_금액을_반환한다() {
        Money money = new Money(-10000);

        assertThat(money.negate()).isEqualTo(new Money(10000));
    }
}
