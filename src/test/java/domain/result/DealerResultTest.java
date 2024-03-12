package domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.gamer.Name;
import domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerResultTest {
    static DealerResult dealerResult;

    @BeforeAll
    static void init() {
        Name name1 = new Name("kaki");
        Name name2 = new Name("lini");
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        Map<Player, Result> results = new LinkedHashMap<>();
        results.put(player1, Result.WIN);
        results.put(player2, Result.LOSE);
        PlayerResults playerResults = new PlayerResults(results);

        dealerResult = DealerResult.createOppositeResult(playerResults);
    }

    @DisplayName("플레이어 패배 수만큼 딜러는 승리한다.")
    @Test
    void getWinCountTest() {
        // given
        int expectedCount = 1;

        // when
        int winCount = dealerResult.getWinCount();

        // then
        assertThat(winCount).isEqualTo(expectedCount);
    }

    @DisplayName("플레이어 우승 수만큼 딜러는 패배한다.")
    @Test
    void getLoseCountTest() {
        // given
        int expectedCount = 1;

        // when
        int loseCount = dealerResult.getLoseCount();

        // then
        assertThat(loseCount).isEqualTo(expectedCount);
    }

    @DisplayName("플레이어가 무승부이면 딜러도 무승부이다.")
    @Test
    void getTieCountTest() {
        // given
        int expectedCount = 0;

        // when
        int tieCount = dealerResult.getTieCount();

        // then
        assertThat(tieCount).isEqualTo(expectedCount);
    }
}
