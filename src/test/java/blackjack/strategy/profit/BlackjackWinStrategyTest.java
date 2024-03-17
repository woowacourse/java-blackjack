package blackjack.strategy.profit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("블랙잭 수익률 전략")
class BlackjackWinStrategyTest {

    @DisplayName("블랙잭으로 승리할 시 수익률을 계산한다.")
    @Test
    void calculate() {
        // given
        ProfitStrategy blackjackWinStrategy = new BlackjackWinStrategy();
        BigDecimal betting = new BigDecimal("10000");

        // when
        BigDecimal actual = blackjackWinStrategy.calculate(betting);

        // then
        assertThat(actual).isEqualTo(new BigDecimal("15000.0"));
    }
}