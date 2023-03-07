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
                .hasMessage(Message.PLAYER_INVALID_NUMBERS.getMessage());
    }

    @DisplayName("플레이어의 수가 1명 미만이면 예외를 반환한다.")
    @Test
    void create_fail_by_players_size_under() {
        // given
        List<Player> players = createPlayers();

        // when && then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Message.PLAYER_INVALID_NUMBERS.getMessage());
    }

    private List<Player> createPlayers(String... names) {
        List<Card> cards = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Status(new Name(name), new Account(10000)), new DrawnCards(cards)));
        }

        return players;
    }

    @DisplayName("플레이어의 이름이 중복이면 예외를 반환한다.")
    @Test
    void create_fail_when_player_names_duplicated() {
        // given
        String givenName = "춘식";
        List<Player> players = createPlayers(givenName, givenName);

        // when && then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Message.PLAYER_NAME_NOT_DUPLICATED.getMessage());
    }

    @DisplayName("플레이어의 이름이 딜러와 같다면 예외를 반환한다.")
    @Test
    void create_fail_when_player_name_is_same_with_dealer() {
        // given
        String givenName = "딜러";
        List<Player> players = createPlayers(givenName);

        // when && then
        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Message.PLAYER_NAME_NOT_DEALER.getMessage());
    }
}
