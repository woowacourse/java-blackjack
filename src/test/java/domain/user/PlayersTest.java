package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("Player들을 반환한다.")
    void 플레이어들_반환() {
        Players players = Players.of(List.of("name1", "name2"));
        assertThat(players.getPlayers()).hasSize(2);
    }
}