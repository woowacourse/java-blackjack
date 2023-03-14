package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

class GameResultTest {

    @DisplayName("GameResult의 findResult메서드는 Player가 승리했을 경우 반대인 패배를 반환한다.")
    @Test
    void returnsLoseWhenPlayerWin() {
        // given
        Map<Player, ResultType> playerResult = new LinkedHashMap<>();

        Players players = Players.createPlayers("pobi,crong");
        for (Player player : players.getPlayers()) {
            playerResult.put(player, ResultType.WIN);
        }

        GameResult gameResult = new GameResult(playerResult);

        // when
        Map<ResultType, Integer> dealerResult = gameResult.findDealerResult();

        // then
        assertThat(dealerResult.get(ResultType.LOSE)).isEqualTo(2);
    }
    
    @DisplayName("GameResult의 findResult메서드는 Player가 패배했을 경우 반대인 승리를 반환한다.")
    @Test
    void returnsWinWhenPlayerLose() {
        // given
        Map<Player, ResultType> playerResult = new LinkedHashMap<>();

        Players players = Players.createPlayers("pobi,crong");
        for (Player player : players.getPlayers()) {
            playerResult.put(player, ResultType.LOSE);
        }

        GameResult gameResult = new GameResult(playerResult);

        // when
        Map<ResultType, Integer> dealerResult = gameResult.findDealerResult();

        // then
        assertThat(dealerResult.get(ResultType.WIN)).isEqualTo(2);
    }

    @DisplayName("GameResult의 findResult메서드는 Player가 무승부인 경우 무승부를 반환한다.")
    @Test
    void returnsDrawWhenPlayerDraw() {
        // given
        Map<Player, ResultType> playerResult = new LinkedHashMap<>();

        Players players = Players.createPlayers("pobi,crong");
        for (Player player : players.getPlayers()) {
            playerResult.put(player, ResultType.PUSH);
        }

        GameResult gameResult = new GameResult(playerResult);

        // when
        Map<ResultType, Integer> dealerResult = gameResult.findDealerResult();

        // then
        assertThat(dealerResult.get(ResultType.PUSH)).isEqualTo(2);
    }
}
