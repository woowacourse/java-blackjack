package domain.money;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Name;
import domain.user.Player;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersMoneyTest {
    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {"WIN,1000", "DRAW,0", "LOSE,-1000"})
    @DisplayName("플레이 결과에 따라 플레이어의 돈을 바꾼다.")
    void changeByPlayerResultsWinTest(GameResult gameResult, int moneyValue) {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new Money(1000))));
        Map<Player, GameResult> playersResult = Map.of(player, gameResult);
        PlayersMoney resultPlayersMoney = playersMoney.changeByPlayersResult(playersResult);

        assertThat(resultPlayersMoney.getPlayersMoney()).containsExactly(Map.entry(player, new Money(moneyValue)));
    }

    @Test
    @DisplayName("딜러의 금액은 플레이어가 잃은 금액이다.")
    void calculateDealerMoneyTest() {
        Player player = new Player(new Name("aa"));
        PlayersMoney playersMoney = new PlayersMoney(new HashMap<>(Map.of(player, new Money(1000))));

        assertThat(playersMoney.calculateDealerMoney()).isEqualTo(-1000);
    }
}
