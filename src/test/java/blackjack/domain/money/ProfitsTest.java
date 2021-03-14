package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfitsTest {

    @Test
    @DisplayName("수익 누적 테스트")
    void finalProfitsByResult() {
        Profits profits = new Profits(new BigDecimal("1000"));
        assertThat(profits.accumulate(new Profits(new BigDecimal("-2000")))).isEqualTo(new Profits(new BigDecimal("-1000")));
    }
}
