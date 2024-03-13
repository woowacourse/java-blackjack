package blackjack.model.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.gamer.Name;
import blackjack.model.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class PlayersResultTest {

    PlayersResult playersResult = new PlayersResult();

    @DisplayName("플레이어 결과를 추가한다.")
    @ParameterizedTest
    @EnumSource(Result.class)
    void addWin(Result result) {
        //given
        Player player = Player.of(Name.from("test"), 100);

        //when
        playersResult.add(player, result);
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(result);
    }

    @DisplayName("플레이어의 결과 확인 시, 등록된 결과가 없을 경우 예외를 발생시킨다.")
    @Test
    void findPlayerResultByNoneResult() {
        //given
        Player player = Player.of(Name.from("test"), 100);

        //when, then
        assertThatThrownBy(() -> playersResult.findPlayerResult(player))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
