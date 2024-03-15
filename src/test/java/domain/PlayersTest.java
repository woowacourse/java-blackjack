package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PlayersTest {

    @DisplayName("플레이어 수가 8명 초과하면 예외를 발생한다")
    @Test
    void playerSize() {
        final List<Player> players = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            players.add(new Player(new Name("teba" + i), new BetAmount(100)));
        }

        assertThatCode(() -> new Players(players)).isInstanceOf(IllegalArgumentException.class);
    }
}
