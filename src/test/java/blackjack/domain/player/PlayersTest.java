package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @DisplayName("플레이어가 없으면 예외가 발생한다.")
    @Test
    void testCreatePlayersWithEmptyEntry() {
        assertThatThrownBy(() -> new Players(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("참가자들 중 이름이 중복되는 경우는 예외가 발생한다.")
    @Test
    void testCreatePlayersWithDuplicateNames() {
        Player player1 = new Player(new PlayerName("pobi"));
        Player player2 = new Player(new PlayerName("pobi"));

        assertThatThrownBy(() -> new Players(List.of(player1, player2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("생성 검증을 모두 통과하면 생성에 성공한다.")
    @Test
    void testCreateWithValidPlayers() {
        Player player1 = new Player(new PlayerName("pobi"));
        Player player2 = new Player(new PlayerName("jason"));

        assertThatCode(() -> new Players(List.of(player1, player2)))
                .doesNotThrowAnyException();
    }

    @DisplayName("주어진 플레이어 이름 리스트로 플레이어들을 생성한다.")
    @Test
    void testCreate() {
        // given
        List<String> playerNames = List.of("pobi", "jason");

        // when
        Players players = Players.create(playerNames);

        // then
        assertThat(players.getPlayers()).contains(
                new Player(new PlayerName("pobi")),
                new Player(new PlayerName("jason"))
        );
    }
}
