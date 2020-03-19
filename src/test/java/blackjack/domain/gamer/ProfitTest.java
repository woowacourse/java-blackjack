package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitTest {

    @Test
    @DisplayName("profit factory method 테스트 int 값을 받아서 생성 가능")
    void profitFactoryMethodFromInt() {
        assertThat(Profit.from(1000).getProfit()).isEqualTo(1000);
    }

    @Test
    @DisplayName("profit factory method 테스트 double을 받아서 생성 가능")
    void profitFactoryMethodFromDouble() {
        assertThat(Profit.from(1000d).getProfit()).isEqualTo(1000);
    }

    @Test
    void profitFactoryMethodOf() {
        BettingMoney bettingMoney = BettingMoney.from(10000);
        double profitRate = 1.5d;
        assertThat(Profit.of(bettingMoney.getBettingMoney(), profitRate).getProfit()).isEqualTo(15000);
    }
}