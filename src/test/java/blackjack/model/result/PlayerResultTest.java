package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Player;
import blackjack.model.results.PlayerProfit;
import blackjack.model.results.PlayerResult;
import blackjack.model.results.Result;
import blackjack.vo.Money;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultTest {
    @DisplayName("플레이어 게임 결과로 전체 플레이어 수익을 계산한다")
    @Test
    void getPlayerProfit() {
        PlayerResult playerResult = new PlayerResult(createResultsForBet());

        PlayerProfit playerProfit = playerResult.calclatePlayerProfit();

        assertThat(playerProfit.getProfits().values())
                .containsExactly(new Money(4500), new Money(4000), new Money(-5000), new Money(0));
    }

    private Map<Player, Result> createResultsForBet() {
        Map<Player, Result> map = new LinkedHashMap<>();
        map.put(getPlayer("ella", new Money(3000)), Result.WIN_BY_BLACKJACK);
        map.put(getPlayer("daon", new Money(4000)), Result.WIN);
        map.put(getPlayer("lily", new Money(5000)), Result.LOSE);
        map.put(getPlayer("pobi", new Money(6000)), Result.PUSH);
        return map;
    }

    private Player getPlayer(String name, Money betAmount) {
        Player player = new Player(name);
        player.betMoney(betAmount);
        return player;
    }
}
