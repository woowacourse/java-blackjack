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
        @CsvSource(value = {"10000,-10000", "0,0", "-10000,10000"})
        @DisplayName("플레이어 수익을 추가하면 이에 따라 딜러 수익이 결정된다.")
        void addInverseToDealerProfit(double userProfit, double dealerProfit) {
            ProfitResult profitResult = new ProfitResult();
            profitResult.putPlayerProfit("james", userProfit);

            Assertions.assertThat(profitResult.getDealerProfit()).isEqualTo(dealerProfit);
        }
    }
}