package domain;

import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerResultsTest {

    @DisplayName("플레이어에 대한 결과를 저장한다.")
    @Test
    void findWinCountTest() {
        // given
        PlayerResults playerResults = new PlayerResults();
        Name name = new Name("pobi");
        Player player = new Player(name);
        Result result = Result.WIN;

        int expectedSize = 1;

        // when
        playerResults.addResult(player,result);

        // then
        assertAll(
                () -> assertThat(playerResults.getResults()).hasSize(expectedSize),
                () -> assertThat(playerResults.getResults().get(player)).isEqualTo(result)
        );
    }
}
