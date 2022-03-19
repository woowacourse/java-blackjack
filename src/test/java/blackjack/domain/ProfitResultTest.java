package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProfitResultTest {

    @Nested
    @DisplayName("putPlayerProfit은")
    class PutPlayerProfit {

        @ParameterizedTest
        @CsvSource(value = {"WIN,-10000", "DRAW,0", "LOSE,10000"})
        @DisplayName("플레이어 수익을 추가하면 이에 따라 딜러 수익이 결정된다.")
        void addInverseToDealerProfit(Score score, double dealerProfit) {
            ProfitResult profitResult = new ProfitResult();
            Player james = new Player("james", 10000);
            profitResult.putPlayerProfit(james, james.getTotalProfit(score));

            Assertions.assertThat(profitResult.getDealerProfit()).isEqualTo(dealerProfit);
        }
    }
}