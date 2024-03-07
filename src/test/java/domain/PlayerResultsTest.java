package domain;

import domain.user.Name;
import domain.user.Player;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static domain.Result.WIN;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultsTest {
    @Test
    void generateDealerResultTest() {
        PlayerResults playerResults = new PlayerResults(
                Map.of(new Player(new Name("a")), WIN));

        DealerResult dealerResult = playerResults.generateDealerResult();
        assertThat(dealerResult.getInformation()).isEqualTo("1패");
    }
}
