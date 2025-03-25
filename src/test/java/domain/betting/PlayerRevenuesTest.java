package domain.betting;

import static org.assertj.core.api.Assertions.assertThat;

import domain.fixture.PlayerNameFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerRevenuesTest {

    @Test
    @DisplayName("플레이어들의 수익을 통해 딜러의 수익을 계산한다.")
    void should_return_dealer_revenue_by_player_revenue() {
        // given
        PlayerRevenue first10000 = new PlayerRevenue(PlayerNameFixture.playerNameFirst, new Revenue(10000));
        PlayerRevenue second10000 = new PlayerRevenue(PlayerNameFixture.playerNameSecond, new Revenue(10000));
        PlayerRevenues playerRevenues = new PlayerRevenues(List.of(first10000, second10000));

        // when
        PlayerRevenue dealerRevenue = playerRevenues.calculateDealerRevenue();

        // then
        assertThat(dealerRevenue.revenueValue()).isEqualTo(-20000);
    }
}
