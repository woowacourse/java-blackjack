package blackjack.domain.result;

import blackjack.domain.game.Hand;
import blackjack.domain.game.Player;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {
    @Test
    void 플레이어에_해당하는_결과를_반환한다() {
        // given
        PlayerResults playerResults = new PlayerResults();

        Player player = new Player("히로", new Hand(), new BetAmount(1_000));
        PlayerResult savedResult = new PlayerResult(player, GameResultType.TIE, new Score(player));
        playerResults.add(savedResult);

        // when
        PlayerResult foundResult = playerResults.findResultByPlayer(player);

        // then
        assertThat(foundResult).isEqualTo(savedResult);
    }
}
