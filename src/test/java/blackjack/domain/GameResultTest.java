package blackjack.domain;

import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    @Test
    @DisplayName("각 플레이어와 딜러의 최종 베팅 결과를 계산하고 가져올 수 있다")
    void calculateResultEachPlayer() {
        Player player1 = Player.from("jamie");
        Player player2 = Player.from("test");
        List<Player> players = List.of(player1, player2);
        List<BetAmount> betAmounts = List.of(
                BetAmount.fromPlayer(10000), BetAmount.from(1000)
        );
        GameResult gameResult = GameResult.of(players, betAmounts);

        gameResult.calculateResultEachPlayer(player1, 2);
        gameResult.calculateResultEachPlayer(player2, 1.5);

        assertAll(
                () -> assertThat(gameResult.eachPlayer(player1)).isEqualTo(BetAmount.from(20000)),
                () -> assertThat(gameResult.eachPlayer(player2)).isEqualTo(BetAmount.from(1500)),
                () -> assertThat(gameResult.dealer()).isEqualTo(BetAmount.from(-21500))
        );
    }
}
