package blackjack.domain.result;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitTest {
    @DisplayName("Profit 객체를 생성한다.")
    @Test
    public void createProfit() {
        Profit profit = new Profit();

        assertThat(profit).isInstanceOf(Profit.class);
    }
}
