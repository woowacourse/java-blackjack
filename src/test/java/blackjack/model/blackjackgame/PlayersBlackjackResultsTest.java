package blackjack.model.blackjackgame;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.participants.Betting;
import blackjack.model.participants.Name;
import blackjack.model.participants.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PlayersBlackjackResultsTest {

    @DisplayName("딜러 수익률을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3", "10,20,40", "50,10,1000"})
    void getDealerProfit(int first, int second, int third) {
        int expected = -(first + second + third);
        var playersBlackjackResults = createPlayersResults(first, second, third);
        Profit result = playersBlackjackResults.calculateDealerProfit();

        assertThat(result).isEqualTo(new Profit(expected));
    }

    private PlayersBlackjackResults createPlayersResults(int first, int second, int third) {
        Player p1 = new Player(new Name("daon1"), new Betting(0));
        Player p2 = new Player(new Name("daon2"), new Betting(100));
        Player p3 = new Player(new Name("daon3"), new Betting(200));
        Profit profit1 = new Profit(first);
        Profit profit2 = new Profit(second);
        Profit profit3 = new Profit(third);
        Map<Player, Profit> results = new LinkedHashMap<>();
        results.put(p1, profit1);
        results.put(p2, profit2);
        results.put(p3, profit3);
        return new PlayersBlackjackResults(results);
    }
}
