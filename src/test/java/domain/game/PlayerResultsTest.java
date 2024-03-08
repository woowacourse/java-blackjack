package domain.game;

import domain.user.Name;
import domain.user.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.game.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultsTest {
    @Test
    @DisplayName("딜러의 승패 결과를 반환한다.")
    void generateDealerResultTest() {
        PlayerResults playerResults = new PlayerResults(
                Map.of(new Player(new Name("a")), WIN));

        DealerResult dealerResult = playerResults.generateDealerResult();
        assertThat(dealerResult.getResultDetail()).isEqualTo("1패");
    }
}
