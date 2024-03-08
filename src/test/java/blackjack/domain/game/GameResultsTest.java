package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과들")
public class GameResultsTest {
    @Test
    @DisplayName("플레이어의 승패 결과를 Map으로 반환한다.")
    void createPlayersGameResultsTest() {
        // given
        GameResults gameResults = new GameResults();
        Map<String, GameResult> expectedGameResults = new HashMap<>();
        expectedGameResults.put("lemone", GameResult.WIN);

        // when
        gameResults.put("lemone", GameResult.WIN);

        // then
        assertThat(gameResults.getPlayersResults()).isEqualTo(expectedGameResults);
    }

    @Test
    @DisplayName("딜러 승패 결과를 Map으로 반환한다.")
    void createDealerResulTest() {
        // given
        GameResults gameResults = new GameResults();
        Map<GameResult, Integer> expectedResult = new HashMap<>();
        expectedResult.put(GameResult.WIN, 1);
        expectedResult.put(GameResult.LOSE, 2);
        expectedResult.put(GameResult.DRAW, 1);

        // when
        gameResults.put("lemone", GameResult.WIN);
        gameResults.put("seyang", GameResult.LOSE);
        gameResults.put("pobi", GameResult.DRAW);
        gameResults.put("club", GameResult.WIN);

        // then
        assertThat(gameResults.getDealerResult()).isEqualTo(expectedResult);
    }
}
