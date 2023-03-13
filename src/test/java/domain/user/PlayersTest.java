package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    @Test
    @DisplayName("Player들을 반환한다.")
    void 플레이어들_반환() {
        Map<String, Integer> playerBettingMoneyTable = new LinkedHashMap<>() {{
            put("Player1", 1_000);
            put("Player2", 2_000);
        }};
        Players players = Players.from(playerBettingMoneyTable);
        assertThat(players.getPlayers()).hasSize(2);
    }
}
