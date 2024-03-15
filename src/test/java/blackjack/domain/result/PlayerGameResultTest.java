package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.Player;
import blackjack.domain.player.TestPlayerCreator;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들 게임 결과 도메인 테스트")
class PlayerGameResultTest {

    @DisplayName("특정 플레이어의 결과를 조회할 수 있다")
    @Test
    void testFindPlayerResult() {
        Player player1 = TestPlayerCreator.of("리비", 3, 4, 5);
        Player player2 = TestPlayerCreator.of("썬", 3, 4);

        Map<Player, GameResult> playerResultMap = new HashMap<>();
        playerResultMap.put(player1, GameResult.PLAYER_WIN);
        playerResultMap.put(player2, GameResult.PLAYER_LOSE);

        PlayerGameResult playerGameResult = new PlayerGameResult(playerResultMap);
        assertThat(playerGameResult.findGameResultOfPlayer(player1)).isEqualTo(GameResult.PLAYER_WIN);
    }
}
