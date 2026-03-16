package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DealerProfitTest {

    @ParameterizedTest
    @ValueSource(ints = {-1000, 1000, 0, 1200, -1200})
    void dealerProfit(int playerTotalPrize) {
        assertThat(new DealerProfit(playerTotalPrize).getAmount())
                .isEqualTo(playerTotalPrize * -1);
    }
}