package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        Players players = new Players(Arrays.asList("jamie", "ravie"));
        List<String> playerNames = players.getPlayerNames();

        assertThat(playerNames.get(0)).isEqualTo("jamie");
        assertThat(playerNames.get(1)).isEqualTo("ravie");
    }
}
