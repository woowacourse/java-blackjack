package blackjack.domain;

import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Game 클래스")
class GameTest {

    @Test
    @DisplayName("플레이어들의 이름을 입력받아 플레이어스 객체를 생성한다")
    void generatePlayersFromPlayerNames() {
        List<String> names = List.of("pobi", "jason", "brown");

        Game game = new Game(names);
        List<Player> players = game.getPlayers();

        assertThat(players.size()).isEqualTo(3);
    }
}