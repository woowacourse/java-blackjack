package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Betting;
import blackjack.model.participants.Name;
import blackjack.model.participants.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersResultsTest {

    @DisplayName("딜러 수익률을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "10,20,40", "50,10,1000"})
    void getDealerProfit(int first, int second, int third) {
        int expected = -(first + second + third);
        PlayersResults playersResults = new PlayersResults();
        createPlayerResults(first, second, third, playersResults);

        Profit result = playersResults.getDealerProfit();

        assertThat(result).isEqualTo(new Profit(expected));
    }

    private void createPlayerResults(int first, int second, int third, PlayersResults playersResults) {
        playersResults.add(new Player(new Name("daon1"), new Betting(0)), new Profit(first));
        playersResults.add(new Player(new Name("daon2"), new Betting(100)), new Profit(second));
        playersResults.add(new Player(new Name("daon3"), new Betting(200)), new Profit(third));
    }
}
