package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.game.BettingResultAmount;
import domain.game.BettingStatistics;
import domain.game.GameResult;
import domain.participants.BettingAmount;
import domain.participants.Player;
import domain.participants.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BettingStatisticsTest {

    @Test
    @DisplayName("플레이어들의 결과에 따라 배팅 금액 결과를 정상적으로 반환한다.")
    void bettingStatisticsTest() {
        // given
        Map<Player, GameResult> result = initialized();
        // when
        BettingStatistics bettingStatistics = BettingStatistics.fromBettingResult(result);
        Map<PlayerName, BettingResultAmount> bettingResult = bettingStatistics.getBettingResult();
        // then
        BettingResultAmount bettingResultAmount1 = bettingResult.get(new PlayerName("a"));
        BettingResultAmount bettingResultAmount2 = bettingResult.get(new PlayerName("b"));
        BettingResultAmount bettingResultAmount3 = bettingResult.get(new PlayerName("c"));
        assertThat(bettingResultAmount1.getMoney()).isEqualTo(10000);
        assertThat(bettingResultAmount2.getMoney()).isEqualTo(0);
        assertThat(bettingResultAmount3.getMoney()).isEqualTo(-30000);
    }

    @Test
    @DisplayName("게임 결과에 따라 딜러의 전체 수익을 계산한다.")
    void calculateDealerResult() {
        // given
        Map<Player, GameResult> result = initialized();
        // when
        BettingStatistics bettingStatistics = BettingStatistics.fromBettingResult(result);
        // then
        BettingResultAmount dealerResult = bettingStatistics.calculateDealerBettingResult();
        assertThat(dealerResult).isEqualTo(new BettingResultAmount(20000));
    }

    private Map<Player, GameResult> initialized() {
        Player player1 = new Player(new PlayerName("a"), new BettingAmount(10000));
        Player player2 = new Player(new PlayerName("b"), new BettingAmount(20000));
        Player player3 = new Player(new PlayerName("c"), new BettingAmount(30000));
        return Map.of(player1, GameResult.WIN, player2, GameResult.DRAW, player3, GameResult.LOSE);
    }
}
