package domain.participant;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("Players를 정상적으로 생성한다.")
    @Test
    void create_success() {
        // given
        List<Player> expected = createPlayers("pobi", "neo", "ori", "jay");
        // when && then
        assertThatNoException()
                .isThrownBy(() -> new Players(expected));
    }

    @DisplayName("플레이어의 수가 4명 초과하면 예외를 반환한다.")
    @Test
    void create_fail_by_players_size_over() {
        // given
        List<Player> expected = createPlayers("pobi", "neo", "ori", "jay", "odo");
        // when && then
        assertThatThrownBy(() -> new Players(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명, 최대 4명입니다.");
     }

    @DisplayName("플레이어의 수가 1명 미만이면 예외를 반환한다.")
    @Test
    void create_fail_by_players_size_under() {
        // given
        List<Player> expected = createPlayers();
        // when && then
        assertThatThrownBy(() -> new Players(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 최소 1명, 최대 4명입니다.");
    }

    @DisplayName("플레이어의 이름이 중복되면 예외를 반환한다.")
    @Test
    void create_fail_by_duplicated_name() {
        //given
        List<Player> expected = createPlayers("pobi", "pobi");
        //when && then
        assertThatThrownBy(() -> new Players(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 중복될 수 없습니다.");
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
