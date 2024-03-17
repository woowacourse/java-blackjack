package blackjack.strategy.profit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("승리 수익률 전략")
class WinStrategyTest {

    @DisplayName("블랙잭으로 승리할 시 수익률을 계산한다.")
    @Test
    void calculate() {
        // given
        ProfitStrategy winStrategy = new WinStrategy();
        BigDecimal betting = new BigDecimal("10000");

        // when
        BigDecimal actual = winStrategy.calculate(betting);

        // then
        assertThat(actual).isEqualTo(new BigDecimal("10000"));
    }
}