package blackjack.domain.result;

import blackjack.domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class GameResultsTest {

    @DisplayName("딜러의 수익은 '-플레이어_수익' 이다.")
    @Test
    void calculateDealerProfitTest() {
        // given
        Player player1 = new Player("eden", 10000);
        Player player2 = new Player("nak", 10000);
        Player player3 = new Player("kaki", 10000);

        // when
        Map<Player, GameResult> gameResultInfos = new HashMap<>();
        gameResultInfos.put(player1, GameResult.WIN);
        gameResultInfos.put(player2, GameResult.WIN);
        gameResultInfos.put(player3, GameResult.WIN);
        GameResults gameResults = new GameResults(gameResultInfos);

        GamerProfits gamerProfits = gameResults.calculateGamerProfits();

        // then
        Assertions.assertThat(gamerProfits.getDealerProfit()).isEqualTo(-30000);
    }
}
