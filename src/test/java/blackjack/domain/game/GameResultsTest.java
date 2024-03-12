package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과들")
public class GameResultsTest {
    @Test
    @DisplayName("에서 플레이어의 승패 결과를 반환한다.")
    void getPlayersResults() {
        // given
        GameResults gameResults = new GameResults();

        // when
        gameResults.put("lemone", GameResult.WIN);

        // then
        assertThat(gameResults.getPlayersResults())
                .isEqualTo(Map.of("lemone", GameResult.WIN));
    }

    @Test
    @DisplayName("에서 딜러의 승패 결과를 반환한다.")
    void getDealerResult() {
        // given
        GameResults gameResults = new GameResults();
        Map<GameResult, Integer> expectedDealerResult =
                Map.of(GameResult.WIN, 2, GameResult.LOSE, 3, GameResult.DRAW, 1);

        // when
        gameResults.put("lemone", GameResult.WIN);
        gameResults.put("seyang", GameResult.LOSE);
        gameResults.put("pobi", GameResult.DRAW);
        gameResults.put("solla", GameResult.LOSE);
        gameResults.put("club", GameResult.WIN);
        gameResults.put("choco", GameResult.WIN);

        // then
        assertThat(gameResults.getDealerResult()).isEqualTo(expectedDealerResult);
    }
}
