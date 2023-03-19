package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("Player들을 반환한다.")
    void 플레이어들_반환() {
        Players players = new Players(List.of(new Player("name", 10000), new Player("name", 10000)));
        assertThat(players.getPlayers()).hasSize(2);
    }
}
