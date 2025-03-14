package domain.result;

import static org.assertj.core.api.Assertions.*;

import card.Hand;
import participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import result.PlayerResult;
import result.GameStatus;

class PlayerResultTest {

    Map<Player, GameStatus> gameResults = Map.of(
            new Player("mimi", Hand.createEmpty()), GameStatus.WIN,
            new Player("hiro", Hand.createEmpty()), GameStatus.LOSE,
            new Player("hipo", Hand.createEmpty()), GameStatus.LOSE,
            new Player("july", Hand.createEmpty()), GameStatus.LOSE
            );

    @DisplayName("승의 개수를 정상적으로 반환한다")
    @Test
    void test1() {
        //given
        PlayerResult playerResult = new PlayerResult(gameResults);

        //when
        int winCount = playerResult.calculateStatusCount(GameStatus.WIN);

        //then
        assertThat(winCount).isEqualTo(1);
    }

    @DisplayName("패의 개수를 정상적으로 반환한다")
    @Test
    void test3() {
        //given
        PlayerResult playerResult = new PlayerResult(gameResults);

        //when
        int loseCount = playerResult.calculateStatusCount(GameStatus.LOSE);

        //then
        assertThat(loseCount).isEqualTo(3);
    }
}
