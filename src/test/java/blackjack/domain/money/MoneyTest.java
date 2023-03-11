package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("수익률을 넣으면, 소수점을 제외한 수익금을 반환한다.")
    void multiplyTest() {
        final Double profitRate = 1.5;
        final Money money = new Money(1_000);

        final Money profit = money.multiply(profitRate);

        assertThat(profit.getValue()).isEqualTo(1_500);
    }

    @Test
    @DisplayName("값에 -1을 곱해서 반환하는 기능 추가(음수로 변환)")
    void oppositeTest() {
        final int moneyValue = 1_000;
        final Money money = new Money(1_000);

        assertThat(money.opposite().getValue())
                .isEqualTo(moneyValue * -1);
    }

    @Test
    @DisplayName("값을 더해서 반환하는 기능 추가")
    void sumTest() {
        final int[] moneyValues = {1_000, -500};
        final Money money1 = new Money(moneyValues[0]);
        final Money money2 = new Money(moneyValues[1]);

        final Money summedMoney = money1.sum(money2);

        assertThat(summedMoney.getValue())
                .isEqualTo(moneyValues[0] + moneyValues[1]);
    }
}
