package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("이름을 받아서 플레이어들을 생성해줌")
    void players() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        Players players = new Players(playerNames);
        List<Player> playerList = players.getPlayers();
        assertThat(playerList.get(0).getName()).isEqualTo("pobi");
        assertThat(playerList.get(1).getName()).isEqualTo("jason");
    }

    @Test
    @DisplayName("플레이어들의 이름을 받아오는 테스트")
    void getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        playerNames.add("pobi");
        playerNames.add("jason");
        Players players = new Players(playerNames);
        playerNames = players.getPlayerNames();
        assertThat(playerNames.get(0)).isEqualTo("pobi");
        assertThat(playerNames.get(1)).isEqualTo("jason");
    }
}
