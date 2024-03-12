package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersResultTest {

    PlayersResult playersResult = new PlayersResult();

    @DisplayName("플레이어 결과에 '승리'를 추가한다.")
    @Test
    void addWin() {
        //given
        Player player = Player.from("test");

        //when
        playersResult.addWin(player);
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어 결과에 '블랙잭'를 추가한다.")
    @Test
    void addBlackjack() {
        //given
        Player player = Player.from("test");

        //when
        playersResult.addBlackjack(player);
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("플레이어 결과에 '푸시'를 추가한다.")
    @Test
    void addTie() {
        //given
        Player player = Player.from("test");

        //when
        playersResult.addPush(player);
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.PUSH);
    }

    @DisplayName("플레이어 결과에 '패배'를 추가한다.")
    @Test
    void addLose() {
        //given
        Player player = Player.from("test");

        //when
        playersResult.addLose(player);
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }
}
