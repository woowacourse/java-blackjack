package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어 수가 8명 초과하면 예외를 발생한다")
    void playerSize() {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            players.add(new Player(new Name("teba" + i)));
        }

        assertThatCode(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름이 겹치면 예외가 발생한다")
    void duplicateName() {
        final List<Player> players = new ArrayList<>();

        players.add(new Player(new Name("teba")));
        players.add(new Player(new Name("teba")));

        assertThatCode(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어의 이름을 찾을 때 존재하지 않는 이름이라면 예외가 발생한다")
    void nameNotExist() {
        final Players players = Players.fromNames(List.of("a", "b", "c"));

        assertThatCode(() -> players.findPlayerByName("d")).isInstanceOf(IllegalArgumentException.class);
    }
}
