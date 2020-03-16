package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("이름을 받아서 플레이어들을 생성해줌")
    void players() {
        Names playerNames = new Names("pobi,habi");
        Players players = new Players(playerNames);
        List<Player> playerList = players.getPlayers();
        assertThat(playerList.get(0).getName().toString()).isEqualTo("pobi");
        assertThat(playerList.get(1).getName().toString()).isEqualTo("habi");
    }

    @Test
    @DisplayName("플레이어들의 이름을 받아오는 테스트")
    void getPlayerNames() {
        Names playerNames = new Names("pobi,habi");
        Players players = new Players(playerNames);
        List<Name> names = players.getPlayerNames();
        assertThat(names.get(0).toString()).isEqualTo("pobi");
        assertThat(names.get(1).toString()).isEqualTo("habi");
    }
}
