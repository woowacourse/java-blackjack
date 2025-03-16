package blackjack.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    @DisplayName("100% 수익률을 적용하면 원금과 동일한 값이 나온다")
    void applyFullProfit() {
        // given
        Money money = Money.from(10000);

        // when
        Money result = money.applyProfitRate(100);

        // then
        assertThat(result).isEqualTo(Money.from(10000));
    }

    @Test
    @DisplayName("50% 수익률을 적용하면 원금의 절반 값이 나온다")
    void applyHalfProfit() {
        // given
        Money money = Money.from(10000);

        // when
        Money result = money.applyProfitRate(50);

        // then
        assertThat(result).isEqualTo(Money.from(5000));
    }

    @Test
    @DisplayName("0% 수익률을 적용하면 수익이 0이 된다")
    void applyZeroProfit() {
        // given
        Money money = Money.from(10000);

        // when
        Money result = money.applyProfitRate(0);

        // then
        assertThat(result).isEqualTo(Money.from(0));
    }

    @Test
    @DisplayName("-100% 수익률을 적용하면 원금의 음수 값이 나온다")
    void applyNegativeProfit() {
        // given
        Money money = Money.from(10000);

        // when
        Money result = money.applyProfitRate(-100);

        // then
        assertThat(result).isEqualTo(Money.from(-10000));
    }

    @Test
    @DisplayName("소숫점 절삭: 1원의 25% 수익률을 적용하면 0원이 된다")
    void applyProfitWithRoundingDown() {
        // given
        Money money = Money.from(1);

        // when
        Money result = money.applyProfitRate(25);

        // then
        assertThat(result).isEqualTo(Money.from(0));
    }

    @Test
    @DisplayName("0원에 대해 수익률을 적용해도 0원이 유지된다")
    void applyProfitToZeroMoney() {
        // given
        Money money = Money.from(0);

        // when
        Money result1 = money.applyProfitRate(100);
        Money result2 = money.applyProfitRate(0);
        Money result3 = money.applyProfitRate(-50);

        // then
        assertThat(result1).isEqualTo(Money.from(0));
        assertThat(result2).isEqualTo(Money.from(0));
        assertThat(result3).isEqualTo(Money.from(0));
    }

    @Test
    @DisplayName("필드가 같다면 같은 객체로 취급한다")
    void shouldEqualsWhenValuesAreSame() {
        // given
        int amount = 10000;
        Money money1 = Money.from(amount);
        Money money2 = Money.from(amount);

        // when
        // then
        assertThat(money1).isEqualTo(money2);
    }
}
