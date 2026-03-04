import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    void 플레이어를_등록한다() {
        Players players = new Players();
        players.add(new Player("pobi"));
        players.add(new Player("abc"));

        List<Player> records = players.getPlayers();

        assertThat(records).anyMatch(player -> player.getName().equals("abc"));
    }
}
