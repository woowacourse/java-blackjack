package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("새로운 플레이어들을 이름을 통해서 저장하고 가져온다.")
    @Test
    void test1() {
        // given
        List<String> names = List.of("꾹이", "히로");
        List<Player> playersToBeSaved = names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList();

        // when
        Players players = new Players(playersToBeSaved);

        // then
        assertThat(players.getPlayers()).hasSize(2);

    }
}
