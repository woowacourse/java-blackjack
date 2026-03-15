package domain;

import domain.participant.Player;
import domain.result.GameProfit;
import domain.result.GameResult;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameProfitTest {

    @Test
    public void 베팅_금액과_게임_결과에_따라_플레이어별_수익이_정확히_계산되어_저장된다() {
        // given
        Map<Player, GameResult> testMap = Map.of(new Player("토리", 10000), GameResult.WIN,
                new Player("에덴", 20000), GameResult.LOSE,
                new Player("이안", 30000), GameResult.BLACKJACK,
                new Player("녀녕", 40000), GameResult.DRAW);

        // when
        GameProfit gameProfit = new GameProfit(testMap);
        Map<String, Long> playerProfit = gameProfit.getPlayerProfit();

        // then
        Assertions.assertAll(
                () -> Assertions.assertEquals(10000, playerProfit.get("토리")),
                () -> Assertions.assertEquals(-20000, playerProfit.get("에덴")),
                () -> Assertions.assertEquals(45000, playerProfit.get("이안")),
                () -> Assertions.assertEquals(0, playerProfit.get("녀녕"))
        );
    }

    @Test
    public void 딜러의_수익은_모든_플레이어_수익_합에_마이너스를_곱한_값과_같다() {
        // given
        Map<Player, GameResult> testMap = Map.of(new Player("토리", 30000), GameResult.WIN,
                new Player("에덴", 30000), GameResult.LOSE,
                new Player("이안", 10000), GameResult.BLACKJACK,
                new Player("녀녕", 40000), GameResult.DRAW);

        // when
        GameProfit gameProfit = new GameProfit(testMap);
        long dealerProfit = gameProfit.getDealerProfit();
        long expected = -15000;

        // then
        Assertions.assertEquals(expected, dealerProfit);
    }
}
