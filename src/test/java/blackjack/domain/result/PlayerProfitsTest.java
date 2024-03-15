package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.bet.Profit;
import blackjack.domain.player.Player;
import blackjack.domain.player.TestPlayerCreator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어의 수익 도메인 테스트")
class PlayerProfitsTest {

    @DisplayName("특정 플레이어의 수익을 조회할 수 있다")
    @Test
    void testFindProfitOfPlayer() {
        Player player1 = TestPlayerCreator.of("썬", 1, 2, 3, 4);
        Player player2 = TestPlayerCreator.of("리비", 3, 4);

        Map<Player, Profit> playerProfitMap = new HashMap<>();
        playerProfitMap.put(player1, new Profit(30));
        playerProfitMap.put(player2, new Profit(40));
        PlayerProfits playerProfits = new PlayerProfits(playerProfitMap);

        assertThat(playerProfits.findProfitOfPlayer(player1)).isEqualTo(new Profit(30));
    }

    @DisplayName("전체 플레이어들의 수익을 총합계산할 수 있다")
    @Test
    void testCalculateTotalProfit() {
        Player player1 = TestPlayerCreator.of("썬", 1, 2, 3, 4);
        Player player2 = TestPlayerCreator.of("리비", 3, 4);

        Map<Player, Profit> playerProfitMap = new HashMap<>();
        playerProfitMap.put(player1, new Profit(-30000));
        playerProfitMap.put(player2, new Profit(40000));
        PlayerProfits playerProfits = new PlayerProfits(playerProfitMap);

        assertThat(playerProfits.calculateTotalProfit().getValue()).isEqualTo(10000);
    }

    @DisplayName("딜러의 수익을 계산할 수 있다")
    @Test
    void testCalculateDealerProfit() {
        Player player1 = TestPlayerCreator.of("썬", 1, 2, 3, 4);
        Player player2 = TestPlayerCreator.of("리비", 3, 4);

        Map<Player, Profit> playerProfitMap = new HashMap<>();
        playerProfitMap.put(player1, new Profit(-30000));
        playerProfitMap.put(player2, new Profit(40000));
        PlayerProfits playerProfits = new PlayerProfits(playerProfitMap);

        assertThat(playerProfits.calculateDealerProfit().getValue()).isEqualTo(-10000);
    }
}
