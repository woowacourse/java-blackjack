package blackjack.strategy.profit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("패배 수익률 전략")
class LoseStrategyTest {

    @DisplayName("패배할 시 수익률을 계산한다.")
    @Test
    void calculate() {
        // given
        ProfitStrategy loseStrategy = new LoseStrategy();
        BigDecimal betting = new BigDecimal("10000");

        // when
        BigDecimal actual = loseStrategy.calculate(betting);

        // then
        assertThat(actual).isEqualTo(new BigDecimal("-10000"));
    }
}