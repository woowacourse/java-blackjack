package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.gamer.Name;
import domain.gamer.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerResultsTest {
    PlayerResults playerResults;

    @BeforeEach
    void init() {
        Name name1 = new Name("pobi");
        Name name2 = new Name("jason");
        Name name3 = new Name("solar");
        Name name4 = new Name("neo");
        Player pobi = new Player(name1);
        Player jason = new Player(name2);
        Player solar = new Player(name3);
        Player neo = new Player(name4);

        Map<Player, Result> results = new LinkedHashMap<>();
        results.put(pobi, Result.WIN);
        results.put(jason, Result.LOSE);
        results.put(solar, Result.WIN);
        results.put(neo, Result.TIE);

        playerResults = new PlayerResults(results);
    }

    @DisplayName("우승한 플레이어의 수를 구한다.")
    @Test
    void findWinCountTest() {
        // given
        int expectedWinCount = 2;

        // when
        int winCount = playerResults.findWinCount();

        // then
        assertThat(winCount).isEqualTo(expectedWinCount);
    }

    @DisplayName("패배한 플레이어의 수를 구한다.")
    @Test
    void findLoseCountTest() {
        // given
        int expectedLoseCount = 1;

        // when
        int loseCount = playerResults.findLoseCount();

        // then
        assertThat(loseCount).isEqualTo(expectedLoseCount);
    }

    @DisplayName("무승부한 플레이어의 수를 구한다.")
    @Test
    void findTieCountTest() {
        // given
        int expectedTieCount = 1;

        // when
        int tieCount = playerResults.findTieCount();

        // then
        assertThat(tieCount).isEqualTo(expectedTieCount);
    }
}
