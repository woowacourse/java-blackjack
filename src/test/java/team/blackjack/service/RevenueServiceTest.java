package team.blackjack.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;
import team.blackjack.domain.Player;
import team.blackjack.domain.Result;
import team.blackjack.service.dto.RevenueResult;

class RevenueServiceTest {

    private final RevenueService revenueService = new RevenueService();

    @Test
    void 승_시_배팅금_그대로_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = revenueService.calculatePlayerRevenue(player, Result.WIN);

        assertThat(revenue).isEqualTo(1000.0);
    }

    @Test
    void 패_시_배팅금_음수_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = revenueService.calculatePlayerRevenue(player, Result.LOSE);

        assertThat(revenue).isEqualTo(-1000.0);
    }

    @Test
    void 무_시_수익_0() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = revenueService.calculatePlayerRevenue(player, Result.DRAW);

        assertThat(revenue).isEqualTo(0.0);
    }

    @Test
    void 블랙잭_승_시_1점5배_수익() {
        Player player = playerWithBatMoney("pobi", 1000);

        double revenue = revenueService.calculatePlayerRevenue(player, Result.BLACKJACK);

        assertThat(revenue).isEqualTo(1500.0);
    }

    @Test
    void 플레이어_한명_승_시_딜러_수익은_플레이어_수익의_음수() {
        Player pobi = playerWithBatMoney("pobi", 1000);
        Map<Player, Result> judgeResults = Map.of(pobi, Result.WIN);

        RevenueResult result = revenueService.getRevenueResult(judgeResults);

        assertThat(result.playerRevenueMap()).containsEntry("pobi", 1000.0);
        assertThat(result.dealerRevenue()).isEqualTo(-1000.0);
    }

    @Test
    void 플레이어_여러명_딜러_수익은_플레이어_수익_합의_음수() {
        Player pobi = playerWithBatMoney("pobi", 1000);
        Player jason = playerWithBatMoney("jason", 500);
        Map<Player, Result> judgeResults = Map.of(
                pobi, Result.WIN,
                jason, Result.LOSE
        );

        RevenueResult result = revenueService.getRevenueResult(judgeResults);

        assertThat(result.playerRevenueMap()).containsEntry("pobi", 1000.0);
        assertThat(result.playerRevenueMap()).containsEntry("jason", -500.0);
        double playerSum = result.playerRevenueMap().values().stream().mapToDouble(Double::doubleValue).sum();
        assertThat(result.dealerRevenue()).isEqualTo(-playerSum);
    }


    private static Player playerWithBatMoney(String name, double batMoney) {
        Player player = new Player(name);
        player.bat(batMoney);
        return player;
    }
}
