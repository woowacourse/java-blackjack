package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfit;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {
    @DisplayName("플레이어 게임 결과로 전체 플레이어 수익을 계산한다")
    @Test
    void getPlayerProfit() {
        PlayerResult playerResult = new PlayerResult(createResultsForBet());

        PlayerProfit playerProfit = playerResult.getPlayerProfit();

        assertThat(playerProfit.getResult().values()).containsExactly(4500, 4000, -5000, 0);
    }

    private Map<Player, Result> createResultsForBet() {
        Map<Player, Result> map = new LinkedHashMap<>();
        map.put(getPlayer("ella", 3000), Result.WIN_BY_BLACKJACK);
        map.put(getPlayer("daon", 4000), Result.WIN);
        map.put(getPlayer("lily", 5000), Result.LOSE);
        map.put(getPlayer("pobi", 6000), Result.PUSH);
        return map;
    }

    private Player getPlayer(String name, int betAmount) {
        Player player = new Player(name);
        player.betMoney(betAmount);
        return player;
    }
}
