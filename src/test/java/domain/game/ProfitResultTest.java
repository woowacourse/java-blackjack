package domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import domain.participant.BettingMoney;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitResultTest {

    @DisplayName("딜러 수익은 플레이어 수익 합산의 부호 반전이다")
    @Test
    void 딜러_수익은_플레이어_수익_합산의_부호_반전이다() {
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        playerProfits.put(new Player("pobi", new BettingMoney(10000)), 10000);
        playerProfits.put(new Player("jason", new BettingMoney(20000)), -20000);

        ProfitResult profitResult = new ProfitResult(playerProfits);

        assertThat(profitResult.getDealerProfit()).isEqualTo(10000);
    }

    @DisplayName("플레이어별 수익을 반환한다")
    @Test
    void 플레이어별_수익을_반환한다() {
        Player player = new Player("pobi", new BettingMoney(10000));
        Map<Player, Integer> playerProfits = new LinkedHashMap<>();
        playerProfits.put(player, 15000);

        ProfitResult profitResult = new ProfitResult(playerProfits);

        assertThat(profitResult.getPlayerProfits().get(player)).isEqualTo(15000);
    }
}
