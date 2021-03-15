package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.Money;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("플레이어는 최소 1명 이상이어야 한다.")
    @Test
    void create() {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new Players(new ArrayList<>()));
    }

    @DisplayName("이름과 배팅금액 목록을 이용해 플레이어들을 만든다.")
    @Test
    void makePlayers() {
        List<String> names = new ArrayList<>(Arrays.asList("choonsik", "papi"));
        List<Money> monies = new ArrayList<>(Arrays.asList(new Money(500), new Money(1000)));
        Players players = Players.makePlayers(names, monies);
        assertThat(players).isEqualTo(new Players(Arrays.asList(
            new Player("choonsik", 500),
            new Player("papi", 1000)
        )));
    }

    @DisplayName("플레이어들의 이름들을 문자열로 가져올 수 있다.")
    @Test
    void playersName() {
        Players players = new Players(Arrays.asList(
            new Player("choonsik", 500),
            new Player("joanne", 1000)
        ));

        assertThat(players.getPlayersName()).isEqualTo("choonsik, joanne");
    }

    @DisplayName("플레이어들 리스트를 가져올 수 있다.")
    @Test
    void get() {
        Players players = new Players(Arrays.asList(
            new Player("choonsik", 500),
            new Player("joanne", 1000)
        ));

        List<Player> expected = Arrays.asList(
            new Player("choonsik", 500),
            new Player("joanne", 1000));
        assertThat(players.getPlayers()).isEqualTo(expected);
    }
}
