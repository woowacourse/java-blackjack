package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    Map<Player, GameResultStatus> gameResults = Map.of(
            new Player("mimi", Cards.createEmpty()), GameResultStatus.WIN,
            new Player("wade", Cards.createEmpty()), GameResultStatus.DRAW,
            new Player("pobi", Cards.createEmpty()), GameResultStatus.DRAW,
            new Player("hiro", Cards.createEmpty()), GameResultStatus.LOSE,
            new Player("hipo", Cards.createEmpty()), GameResultStatus.LOSE,
            new Player("july", Cards.createEmpty()), GameResultStatus.LOSE
            );

    @DisplayName("승의 개수를 정상적으로 반환한다")
    @Test
    void test1() {
        //given
        GameResult gameResult = new GameResult(gameResults);

        //when
        int winCount = gameResult.calculateStatusCount(GameResultStatus.WIN);

        //then
        assertThat(winCount).isEqualTo(1);
    }

    @DisplayName("무의 개수를 정상적으로 반환한다")
    @Test
    void test2() {
        //given
        GameResult gameResult = new GameResult(gameResults);

        //when
        int drawCount = gameResult.calculateStatusCount(GameResultStatus.DRAW);

        //then
        assertThat(drawCount).isEqualTo(2);
    }

    @DisplayName("패의 개수를 정상적으로 반환한다")
    @Test
    void test3() {
        //given
        GameResult gameResult = new GameResult(gameResults);

        //when
        int loseCount = gameResult.calculateStatusCount(GameResultStatus.LOSE);

        //then
        assertThat(loseCount).isEqualTo(3);
    }
}
