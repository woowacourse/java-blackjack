package blackjack.domain.game;

import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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
        playersGameResult.put(new Player("lemone", new Hand(List.of())), PlayerGameResult.WIN);

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
        playersGameResult.put(new Player("lemone", new Hand(List.of())), PlayerGameResult.WIN);
        playersGameResult.put(new Player("seyang", new Hand(List.of())), PlayerGameResult.LOSE);
        playersGameResult.put(new Player("pobi", new Hand(List.of())), PlayerGameResult.PUSH);
        playersGameResult.put(new Player("club", new Hand(List.of())), PlayerGameResult.WIN);

        // then
        assertThat(playersGameResult.getDealerResult()).isEqualTo(expectedResult);
    }
}
