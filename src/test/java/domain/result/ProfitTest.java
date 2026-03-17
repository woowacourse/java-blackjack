package domain.result;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @Test
    void 수익금을_계산하고_저장한다() {
        Profit profit = new Profit(20_000, Result.BLACKJACK);

        int profitAmount = profit.getAmount();

        assertThat(profitAmount).isEqualTo(30_000);
    }

}