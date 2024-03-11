package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.Players;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("중복된 이름이 있으면 예외가 발생한다.")
    void duplicatedNamesTest() {
        List<String> names = List.of("loki", "pedro", "loki");

        assertThatThrownBy(() -> new Players(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재합니다.");
    }

    @Test
    @DisplayName("플레이어들을 저장한다..")
    void getPlayersTest() {
        List<String> names = List.of("loki", "pedro", "jazz");

        Players players = new Players(names);
        assertThat(players.getPlayers()).hasSize(3);
    }

    @Test
    @DisplayName("플레이어들의 이름을 가져온다.")
    void getPlayerNamesTest() {
        List<String> names = List.of("loki", "pedro", "jazz");

        Players players = new Players(names);
        assertThat(players.getPlayerNames()).containsAll(names);
    }
}
