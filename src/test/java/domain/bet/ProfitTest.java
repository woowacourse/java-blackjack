package domain.bet;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("베팅 금액과 수익률로 수익을 계산한다.")
    @Test
    void 배팅_수익_계산() {
        Profit profit = Profit.calculate(Money.bet(10_000), 1.5);

        assertThat(profit).isEqualTo(Profit.of(15_000));
    }

    @DisplayName("수익끼리 더할 수 있다.")
    @Test
    void 수익_더하기() {
        Profit profit = Profit.of(10_000).plus(Profit.of(-5_000));

        assertThat(profit).isEqualTo(Profit.of(5_000));
    }

    @DisplayName("수익의 부호를 반전할 수 있다.")
    @Test
    void 수익_금액_부호_반전() {
        Profit profit = Profit.of(10_000).negate();

        assertThat(profit).isEqualTo(Profit.of(-10_000));
    }
}
