package blackjack.domain.game;

import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Gamer;
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
        Player player = new Player(new Gamer(new Hand(List.of())), "lemone");
        PlayersGameResult playersGameResult = new PlayersGameResult();

        Map<String, PlayerWinStatus> expectedGameResults = new HashMap<>();
        expectedGameResults.put("lemone", PlayerWinStatus.WIN);

        // when
        playersGameResult.put(player, PlayerWinStatus.WIN);

        // then
        assertThat(playersGameResult.getPlayersNameAndResults())
                .isEqualTo(expectedGameResults);
    }

    @Test
    @DisplayName("딜러 승패 결과를 Map으로 반환한다.")
    void createDealerResulTest() {
        // given
        PlayersGameResult playersGameResult = new PlayersGameResult();
        Map<PlayerWinStatus, Integer> expectedResult = new HashMap<>();
        expectedResult.put(PlayerWinStatus.WIN, 1);
        expectedResult.put(PlayerWinStatus.LOSE, 2);
        expectedResult.put(PlayerWinStatus.PUSH, 1);

        // when
        playersGameResult.put(
                new Player(new Gamer(new Hand(List.of())), "lemone"),
                PlayerWinStatus.WIN);
        playersGameResult.put(
                new Player(new Gamer(new Hand(List.of())), "seyang"),
                PlayerWinStatus.LOSE);
        playersGameResult.put(
                new Player(new Gamer(new Hand(List.of())), "pobi"),
                PlayerWinStatus.PUSH);
        playersGameResult.put(
                new Player(new Gamer(new Hand(List.of())), "club"),
                PlayerWinStatus.WIN);

        // then
        assertThat(playersGameResult.getDealerResult())
                .isEqualTo(expectedResult);
    }
}
