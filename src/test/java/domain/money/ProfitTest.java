package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {
    @Test
    @DisplayName("블랙잭 배수만큼 곱한다.")
    void changeByBlackJackTest() {
        Profit profit = new Profit(10000);

        Profit newAmount = profit.changeByBlackjack();

        assertThat(newAmount.value()).isEqualTo(15000);
    }
}
