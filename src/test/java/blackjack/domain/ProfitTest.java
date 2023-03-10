package blackjack.domain;

import blackjack.domain.participant.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @DisplayName("결과에 따라 수익이 정상적으로 생성된다.")
    @Test
    void calculateProfit() {
        // given
        double money = 1000;
        Profit profit = Profit.of(money);

        // when
        double finalProfit1 = profit.calculateProfit(Result.WIN, true);
        double finalProfit2 = profit.calculateProfit(Result.LOSE, true);
        double finalProfit3 = profit.calculateProfit(Result.PUSH, true);

        // then
        assertThat(finalProfit1).isEqualTo(1500.0);
        assertThat(finalProfit2).isEqualTo(-1000.0);
        assertThat(finalProfit3).isEqualTo(1000.0);
    }
}
