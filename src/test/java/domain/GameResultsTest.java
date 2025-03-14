package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultsTest {

    Map<Player, GameResultStatus> gameResults = Map.of(
            new Player("mimi", Hand.createEmpty()), GameResultStatus.WIN,
            new Player("wade", Hand.createEmpty()), GameResultStatus.DRAW,
            new Player("pobi", Hand.createEmpty()), GameResultStatus.DRAW,
            new Player("hiro", Hand.createEmpty()), GameResultStatus.LOSE,
            new Player("hipo", Hand.createEmpty()), GameResultStatus.LOSE,
            new Player("july", Hand.createEmpty()), GameResultStatus.LOSE
            );

    @DisplayName("승의 개수를 정상적으로 반환한다")
    @Test
    void test1() {
        //given
        GameResults gameResults = new GameResults(this.gameResults);

        //when
        int winCount = gameResults.calculateStatusCount(GameResultStatus.WIN);

        //then
        assertThat(winCount).isEqualTo(1);
    }

    @DisplayName("무의 개수를 정상적으로 반환한다")
    @Test
    void test2() {
        //given
        GameResults gameResults = new GameResults(this.gameResults);

        //when
        int drawCount = gameResults.calculateStatusCount(GameResultStatus.DRAW);

        //then
        assertThat(drawCount).isEqualTo(2);
    }

    @DisplayName("패의 개수를 정상적으로 반환한다")
    @Test
    void test3() {
        //given
        GameResults gameResults = new GameResults(this.gameResults);

        //when
        int loseCount = gameResults.calculateStatusCount(GameResultStatus.LOSE);

        //then
        assertThat(loseCount).isEqualTo(3);
    }
}
