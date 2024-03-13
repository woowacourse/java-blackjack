package blackjack.domain.participants;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("1000원의 1.5배는 1500이다.")
    void multiple() {
        Profit profit = new Profit(1000);

        Profit multiple = profit.multiple(1.5);

        Assertions.assertThat(multiple.getProfit()).isEqualTo(1500);
    }

    @Test
    @DisplayName("1000원의 inverse는 -1000이다.")
    void inverse() {
        Profit profit = new Profit(1000);

        Profit inverse = profit.inverse();

        Assertions.assertThat(inverse.getProfit()).isEqualTo(-1000);
    }

    @Test
    @DisplayName("-1000원인 경우 isPositive 는 false 가 나온다.")
    void isPositive() {
        Profit profit = new Profit(1000);

        Profit inverse = profit.inverse();

        Assertions.assertThat(inverse.isPositive()).isFalse();
    }
}
