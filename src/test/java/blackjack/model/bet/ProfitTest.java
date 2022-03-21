package blackjack.model.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitTest {
    private Profit profit;

    @BeforeEach
    void initializeProfit() {
        this.profit = new Profit(new Amount(1000));
    }

    @DisplayName("null을 더한 결과는 더하기 전과 동일하다.")
    @Test
    void add_null() {
        assertThat(profit.add(null).getValue()).isEqualTo(1000);
    }

    @DisplayName("1000원에 2000원을 더하면 3000원이다.")
    @Test
    void add_2000_3000() {
        assertThat(profit.add(new Profit(new Amount(2000))).getValue()).isEqualTo(3000);
    }
}
