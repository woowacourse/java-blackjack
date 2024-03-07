package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
}
