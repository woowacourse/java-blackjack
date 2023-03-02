package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players를 정상적으로 생성한다.")
    @Test
    void create_success() {
        // given
        List<Player> players = createPlayers("pobi", "neo", "ori", "jay");

        // when && then
        assertThatNoException()
                .isThrownBy(() -> new Players(players));
    }

    @DisplayName("플레이어의 수가 4명 초과하면 예외를 반환한다.")
    @Test
    void create_fail_by_players_size_over() {
        // given
        List<Player> players = createPlayers("pobi", "neo", "ori", "jay", "odo");

        // when && then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명, 최대 4명입니다.");
     }

    @DisplayName("플레이어의 수가 1명 미만이면 예외를 반환한다.")
    @Test
    void create_fail_by_players_size_under() {
        // given
        List<Player> players = createPlayers();

        // when && then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명, 최대 4명입니다.");
    }

    public List<Player> createPlayers(String... names) {
        List<Card> cards = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name), new DrawnCards(cards)));
        }

        return players;
    }
}
