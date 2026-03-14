package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SettlementTest {

    @Test
    @DisplayName("게임 결과를 바탕으로 딜러의 총 수익을 정확히 계산한다.")
    void calculateDealerProfitTest() {
        // given
        Player player1 = new Player("luke", 1000);
        Player player2 = new Player("sm", 2000);

        Map<Player, GameResult> results = new LinkedHashMap<>();
        results.put(player1, GameResult.WIN);
        results.put(player2, GameResult.LOSE);
        // when
        Settlement settlement = new Settlement(results);
        // then
        assertThat(settlement.getDealerProfit()).isEqualTo(new Profit(1000.0));
    }

    @Test
    @DisplayName("특정 플레이어의 게임 결과에 따른 수익을 정확히 계산하여 Map에 보관한다.")
    void playerProfitTest() {
        // given
        Player player = new Player("luke", 10000);
        Map<Player, GameResult> results = Map.of(player, GameResult.WIN);
        // when
        Settlement settlement = new Settlement(results);
        // then

        assertThat(settlement.getPlayerProfits().get(player)).isEqualTo(new Profit(10000.0));
    }
}
