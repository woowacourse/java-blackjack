package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @DisplayName("플레이어 최대인원 초과시 예외 발생")
    @Test
    void validMaximumPlayerCount() {
        List<String> playerNames = Arrays
            .asList("jamie1", "jamie2", "jamie3", "jamie4", "jamie5", "jamie6", "jamie7", "jamie8");
        assertThatThrownBy(() -> new Players(playerNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여 인원");
    }

    @DisplayName("플레이어의 이름을 받지 않을시 예외 발생")
    @Test
    void validNotBlankPlayerNames() {
        List<String> playerNamesCaseNull = new ArrayList<>();
        List<String> playerNamesCaseNulls = Arrays.asList("", "", "");

        assertThatThrownBy(() -> new Players(playerNamesCaseNull))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백");
        assertThatThrownBy(() -> new Players(playerNamesCaseNulls))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("공백");
    }

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        Players players = new Players(Arrays.asList("jamie", "ravie"));
        List<String> playerNames = players.getPlayerNames();

        assertThat(playerNames.get(0)).isEqualTo("jamie");
        assertThat(playerNames.get(1)).isEqualTo("ravie");
    }
}
