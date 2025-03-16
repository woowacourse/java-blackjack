package blackjack.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @Test
    @DisplayName("Profit의 음수를 반환한다")
    void profit_test1() {
        Profit profit = Profit.from(100);

        Assertions.assertThat(profit.negate().value()).isEqualTo(-100);
    }

    @Test
    @DisplayName("Profit의 합을 누적한다")
    void profit_test2() {
        Profit profit = Profit.from(100);
        profit = profit.addProfit(Profit.from(100));
        Assertions.assertThat(profit.value()).isEqualTo(200);
    }
}
