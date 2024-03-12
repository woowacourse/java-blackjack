package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Betting;
import blackjack.model.participants.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersResultsTest {

    @DisplayName("딜러 수익률을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "10,20,40", "50,10,1000"})
    void getDealerProfit(int first, int second, int third) {
        int expected = -(first + second + third);
        Map<Player, Profit> playerResults = createPlayerResults(first, second, third);
        PlayersResults playersResults = new PlayersResults(playerResults);

        Profit result = playersResults.getDealerProfit();

        assertThat(result).isEqualTo(new Profit(expected));
    }

    private Map<Player, Profit> createPlayerResults(int first, int second, int third) {
        return Map.of(
                new Player("daon1", new Betting(0)), new Profit(first),
                new Player("daon2", new Betting(100)), new Profit(second),
                new Player("daon3", new Betting(200)), new Profit(third)
        );
    }
}
