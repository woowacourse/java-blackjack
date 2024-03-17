package blackjack.strategy.profit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("무승부 수익률 전략")
class DrawStrategyTest {

    @DisplayName("무승부일 시 수익률을 계산한다.")
    @Test
    void calculate() {
        // given
        ProfitStrategy drawStrategy = new DrawStrategy();
        BigDecimal betting = new BigDecimal("10000");

        // when
        BigDecimal actual = drawStrategy.calculate(betting);

        // then
        assertThat(actual).isEqualTo(BigDecimal.ZERO);
    }
}