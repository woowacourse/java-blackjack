package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RevenuesTest {

    @Test
    @DisplayName("플레이어들의 수익을 통해 딜러의 수익을 계산한다.")
    void should_return_dealer_revenue_by_player_revenue() {
        // given
        int playerRevenueAmount = 10000;
        Revenue playerRevenue = new Revenue("a", playerRevenueAmount);
        Revenues revenues = new Revenues(List.of(playerRevenue));

        // when
        Revenue dealerRevenue = revenues.calculateDealerRevenue();

        // then
        assertThat(dealerRevenue.money()).isEqualTo(-playerRevenueAmount);
    }
}
