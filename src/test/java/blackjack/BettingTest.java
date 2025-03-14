package blackjack;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.GameRound;
import blackjack.domain.gamer.Player;

class BettingTest {

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정한다.")
    void playerStartBettingTest() {
        Player player = new Player("pobi");
        GameRound round = new GameRound();
        assertThatCode(() -> round.betting(player, 100))
            .doesNotThrowAnyException();
    }
}
