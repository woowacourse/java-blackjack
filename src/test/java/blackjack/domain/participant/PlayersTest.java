package blackjack.domain.participant;

import blackjack.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    @Test
    @DisplayName("이름들을 입력받아서 플레이어들을 생성")
    void testCreatePlayers() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("brown"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = new Players(playerList);
        assertThat(players.toList()).hasSize(3);
    }

    @Test
    @DisplayName("NULL 혹은 공백을 입력했을 시 예외처리")
    void testNullOrBlankException() {
        assertThatThrownBy(() -> new Players(Collections.singletonList(
                new Player(new Name(""), Money.of(1)))))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Collections.singletonList(
                new Player(new Name(" "), Money.of(1)))))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Players(Collections.singletonList(
                new Player(new Name(null), Money.of(1))
        ))).isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = new Players(playerList);
        Dealer dealer = new Dealer();
        players.initHandByDealer(dealer);
        assertThat(players.toList()
                .stream()
                .filter(player -> player.toHandList().size() == 2)
                .count())
                .isEqualTo(2);
    }

    @Test
    @DisplayName("Players 일급 컬렉션에 stream() 메서드 바로 사용할 수 있다.")
    void streamTest() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = new Players(playerList);

        assertThat(players.stream()).isInstanceOf(Stream.class);
    }

    @Test
    @DisplayName("Players 일급 컬렉션에 map() 메서드 사용할 수 있다.")
    void mapTest() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = new Players(playerList);

        assertThat(players.map(player -> player)).isInstanceOf(Stream.class);
    }
}