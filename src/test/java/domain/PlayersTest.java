package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.gamer.Name;
import domain.gamer.Player;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @DisplayName("플레이어 수가 2명 미만, 8명 초과이면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 9})
    void invalidPlayerCountTest(int count) {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = "이름" + i;
            players.add(new Player(new Name(name)));
        }

        // then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.INVALID_PLAYER_COUNT);
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 던진다.")
    @Test
    void duplicatedPlayerNameTest() {
        // given
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            String name = "이름";
            players.add(new Player(new Name(name)));
        }

        // then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Players.DUPLICATED_PLAYER_NAME);
    }
}
