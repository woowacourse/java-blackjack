package blackjack.domain.gameresult;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @DisplayName("GameResult는 딜러의 수익 합산을 반환한다")
    @Test
    void should_returnDealerProfit() {
        Map<Player, Profit> playerProfits = new LinkedHashMap<>();
        playerProfits.put(new Player("pobi"), Profit.from(100.0));
        playerProfits.put(new Player("coli"), Profit.from(-50.0));
        GameResult gameResult = new GameResult(playerProfits);

        double expectedProfit = -50.0;
        double actualProfit = gameResult.getDealerProfit();

        assertThat(actualProfit).isEqualTo(expectedProfit);
    }

    @DisplayName("GameResult는 플레이어별 수익 결과를 반환한다")
    @Test
    void should_returnProfitResult() {
        Map<Player, Profit> playerProfits = new LinkedHashMap<>();
        playerProfits.put(new Player("pobi"), Profit.from(100.0));
        playerProfits.put(new Player("coli"), Profit.from(-50.0));
        GameResult gameResult = new GameResult(playerProfits);

        List<Profit> actualProfitResult = gameResult.getProfitResult()
                .values()
                .stream()
                .toList();

        assertThat(actualProfitResult.get(0)).isEqualTo(Profit.from(100.0));
        assertThat(actualProfitResult.get(1)).isEqualTo(Profit.from(-50.0));
    }
}
