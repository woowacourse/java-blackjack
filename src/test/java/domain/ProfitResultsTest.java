package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitResultsTest {

    @DisplayName("딜러의 수익을 계산한다")
    @Test
    void test() {
        // given
        Player player1 = new Player("웨이드1");
        Profit profit1 = new Profit(BigDecimal.valueOf(10000));

        Player player2 = new Player("웨이드2");
        Profit profit2 = new Profit(BigDecimal.valueOf(20000));

        Player player3 = new Player("웨이드3");
        Profit profit3 = new Profit(BigDecimal.valueOf(-10000));

        Map<Player, Profit> profitResult = Map.of(
                player1, profit1,
                player2, profit2,
                player3, profit3
        );
        ProfitResults profitResults = new ProfitResults(profitResult);
        // when
        int dealerProfit = profitResults.calculateDealerProfit();
        // then
        assertThat(dealerProfit).isEqualTo(-(profit1.toIntValue() + profit2.toIntValue() + profit3.toIntValue()));
    }
}
