
package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @Test
    @DisplayName("딜러 수익은 플레이어 수익의 합에 반대다.")
    void getDealerProfit() {
        Dealer dealer = Dealer.create();
        Player pobi = Player.from("pobi", new Money(10000));
        Player jason = Player.from("jason", new Money(20000));

        Map<Player, MatchResult> playerResults = new LinkedHashMap<>();
        playerResults.put(pobi, MatchResult.LOSE);
        playerResults.put(jason, MatchResult.WIN);

        Result result = new Result(dealer, playerResults);

        assertThat(result.getDealerProfit()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 배팅 금액의 1.5배를 얻는다.")
    void getPlayerProfit_blackjack() {
        Player player = createPlayer(110);
        Result result = createResult(player, MatchResult.BLACKJACK);

        assertThat(result.getPlayerProfit(player)).isEqualTo(165);
    }

    @Test
    @DisplayName("플레이어가 승리하면 배팅 금액만큼 얻는다.")
    void getPlayerProfit_win() {
        Player player = createPlayer(110);
        Result result = createResult(player, MatchResult.WIN);

        assertThat(result.getPlayerProfit(player)).isEqualTo(110);
    }

    @Test
    @DisplayName("플레이어가 패배하면 배팅 금액만큼 잃는다.")
    void getPlayerProfit_lose() {
        Player player = createPlayer(110);
        Result result = createResult(player, MatchResult.LOSE);

        assertThat(result.getPlayerProfit(player)).isEqualTo(-110);
    }

    @Test
    @DisplayName("플레이어가 무승부면 수익은 0이다.")
    void getPlayerProfit_draw() {
        Player player = createPlayer(110);
        Result result = createResult(player, MatchResult.DRAW);

        assertThat(result.getPlayerProfit(player)).isEqualTo(0);
    }

    private Result createResult(Player player, MatchResult matchResult) {
        Dealer dealer = Dealer.create();
        Map<Player, MatchResult> playerResults = new LinkedHashMap<>();
        playerResults.put(player, matchResult);
        return new Result(dealer, playerResults);
    }

    private Player createPlayer(int bettingMoney) {
        return Player.from("pobi", new Money(bettingMoney));
    }
}
