package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.gamer.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersResultTest {

    @DisplayName("플레이어 결과에 '승리'를 추가한다.")
    @Test
    void addWin() {
        //given
        PlayersResult playersResult = new PlayersResult();
        Player player = new Player("test");

        //when
        playersResult.addWin(player);
        Map<Player, ResultState> result = playersResult.getPlayerResult();
        ResultState playerResult = result.get(player);

        //then
        assertThat(playerResult).isEqualTo(ResultState.WIN);
    }

    @DisplayName("플레이어 결과에 '패배'를 추가한다.")
    @Test
    void addLose() {
        //given
        PlayersResult playersResult = new PlayersResult();
        Player player = new Player("test");

        //when
        playersResult.addLose(player);
        Map<Player, ResultState> result = playersResult.getPlayerResult();
        ResultState playerResult = result.get(player);

        //then
        assertThat(playerResult).isEqualTo(ResultState.LOSE);
    }


    @DisplayName("플레이어 결과에 '무승부'를 추가한다.")
    @Test
    void addTie() {
        //given
        PlayersResult playersResult = new PlayersResult();
        Player player = new Player("test");

        //when
        playersResult.addTie(player);
        Map<Player, ResultState> result = playersResult.getPlayerResult();
        ResultState playerResult = result.get(player);

        //then
        assertThat(playerResult).isEqualTo(ResultState.TIE);
    }
}
