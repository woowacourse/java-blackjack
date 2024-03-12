package domain.game;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Name;
import domain.user.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {
    @Test
    @DisplayName("딜러의 승패 결과를 반환한다.")
    void generateDealerResultTest() {
        PlayerResults playerResults = new PlayerResults(
                Map.of(new Player(new Name("a")), WIN));

        assertThat(playerResults.generateDealerResult()).contains(
                Map.entry(WIN, 0),
                Map.entry(LOSE, 1),
                Map.entry(DRAW, 0)
        );
    }
}
