package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들 테스트")
class GamePlayersTest {

    @DisplayName("플레이어 이름은 중복될 수 없다.")
    @Test
    void shouldThrowException_WhenDuplicatePlayerName() {
        // given
        String belloName = "벨로";

        // when, then
        assertThatCode(() -> GamePlayers.createByPlayerNames(List.of(belloName, belloName)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("참가할 플레이어가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNoPlayer() {
        // when, then
        assertThatCode(() -> GamePlayers.createByPlayerNames(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최소 1명 이상의 플레이어가 있어야 합니다.");
    }

    @DisplayName("게임에 참가한 플레이어 목록을 조회할 때 반환 결과를 수정할 수 없다.")
    @Test
    void getGamePlayersImmutableTest() {
        // given
        GamePlayers gamePlayers = GamePlayers.createByPlayerNames(List.of("벨로", "포비"));

        // when
        List<Player> players = gamePlayers.getPlayers();

        //then
        assertThatCode(() -> players.add(new Player("네오")))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
