package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    @DisplayName("플레이어들을 생성한다")
    void createPlayers() {
        List<Player> playerList = List.of(new Player("앤지"), new Player("마루"));
        Players players = new Players(playerList);

        assertThat(players.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 1명 미만일 시 오류 발생")
    void createPlayersNumberException() {
        List<Player> playerList = List.of();

        assertThatThrownBy(() -> new Players(playerList))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 8명 초과일 시 오류 발생")
    void createPlayersNumberException2() {
        List<Player> playerList = List.of(new Player("1"), new Player("2"), new Player("3"),
            new Player("4"), new Player("5"), new Player("6"),
            new Player("7"), new Player("8"), new Player("9"));

        assertThatThrownBy(() -> new Players(playerList))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
