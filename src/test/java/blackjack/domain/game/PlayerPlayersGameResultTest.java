package blackjack.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게임 결과들")
public class PlayerPlayersGameResultTest {
    @Test
    @DisplayName("플레이어의 승패 결과를 Map으로 반환한다.")
    void createPlayersGameResultsTest() {
        // given
        PlayersGameResult playersGameResult = new PlayersGameResult();
        Map<String, PlayerGameResult> expectedGameResults = new HashMap<>();
        expectedGameResults.put("lemone", PlayerGameResult.WIN);

        // when
        playersGameResult.put("lemone", PlayerGameResult.WIN);

        // then
        assertThat(playersGameResult.getPlayersResults()).isEqualTo(expectedGameResults);
    }

    @Test
    @DisplayName("딜러 승패 결과를 Map으로 반환한다.")
    void createDealerResulTest() {
        // given
        PlayersGameResult playersGameResult = new PlayersGameResult();
        Map<PlayerGameResult, Integer> expectedResult = new HashMap<>();
        expectedResult.put(PlayerGameResult.WIN, 1);
        expectedResult.put(PlayerGameResult.LOSE, 2);
        expectedResult.put(PlayerGameResult.PUSH, 1);

        // when
        playersGameResult.put("lemone", PlayerGameResult.WIN);
        playersGameResult.put("seyang", PlayerGameResult.LOSE);
        playersGameResult.put("pobi", PlayerGameResult.PUSH);
        playersGameResult.put("club", PlayerGameResult.WIN);

        // then
        assertThat(playersGameResult.getDealerResult()).isEqualTo(expectedResult);
    }
}
