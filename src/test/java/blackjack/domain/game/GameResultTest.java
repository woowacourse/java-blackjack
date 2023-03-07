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

    @DisplayName("GameResult의 findDealerResult메서드는 Player의 승무패와 반대되는 값을 계산하여 반환한다.")
    @Test
    void returnsReverseResultOfPlayer() {
        // given
        Map<Player, ResultType> playerResult = new LinkedHashMap<>();

        Players players = new Players("pobi,crong");
        for (Player player : players.getPlayers()) {
            playerResult.put(player, ResultType.WIN);
        }

        GameResult gameResult = new GameResult(playerResult);

        // when
        Map<ResultType, Integer> dealerResult = gameResult.findDealerResult();

        // then
        assertThat(dealerResult.get(ResultType.LOSE)).isEqualTo(2);
    }
}