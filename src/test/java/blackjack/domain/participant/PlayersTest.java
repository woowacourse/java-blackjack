package blackjack.domain.participant;

import blackjack.controller.dto.PlayerRequestDto;
import blackjack.domain.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    private Players players;

    @BeforeEach
    void setUp() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("brown"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        players = Players.createPlayers(playerList);
    }

    @Test
    @DisplayName("이름들을 입력받아서 플레이어들을 생성")
    void testCreatePlayers() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("brown"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = Players.createPlayers(playerList);
        assertThat(players.toList()).hasSize(3);
    }

    @Test
    @DisplayName("NULL 혹은 공백을 입력했을 시 예외처리")
    void testNullOrBlankException() {
        assertThatThrownBy(() -> Players.createPlayers(Collections.singletonList(
                new Player(new Name(""), Money.of(1)))))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.createPlayers(Collections.singletonList(
                new Player(new Name(" "), Money.of(1)))))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Players.createPlayers(Collections.singletonList(
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
        Players players = Players.createPlayers(playerList);
        Dealer dealer = new Dealer();
        dealer.initHand(players);
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
        Players players = Players.createPlayers(playerList);

        assertThat(players.stream()).isInstanceOf(Stream.class);
    }

    @Test
    @DisplayName("Players 일급 컬렉션에 map() 메서드 사용할 수 있다.")
    void mapTest() {
        List<Player> playerList = Arrays.asList(
                new Player(new Name("pobi"), Money.of(1)),
                new Player(new Name("jason"), Money.of(1))
        );
        Players players = Players.createPlayers(playerList);

        assertThat(players.map(player -> player)).isInstanceOf(Stream.class);
    }

    @Test
    @DisplayName("PlayerRequestDto의 List를 매개변수로 Players 생성된다.")
    void valueOfTest() {
        //give
        List<PlayerRequestDto> playerRequestDtos = Arrays.asList(
            new PlayerRequestDto(new Name("pobi"), Money.of(10)),
            new PlayerRequestDto(new Name("jason"), Money.of(10))
        );

        //when
        Players players = Players.valueOf(playerRequestDtos);

        //then
        assertThat(players).isInstanceOf(Players.class);
    }

    @Test
    @DisplayName("get을 호출하면 매개변수로 준 index의 위치에 있는 Player를 반환한다.")
    void getTest() {
        //give
        Player player = players.get(0);

        Name name = new Name("pobi");
        Money money = Money.of(1);

        assertThat(player.getName()).isEqualTo(name.getValue());
        assertThat(player.money).isEqualTo(money);
    }

    @Test
    @DisplayName("size를 호출하면 플레이어의 숫자를 반환한다.")
    void sizeTest() {
        //give
        int size = players.size();

        //then
        assertThat(size).isEqualTo(3);
    }

    /*
    public static Players valueOf(final List<PlayerRequestDto> playerRequestDtos) {
        return new Players(playerRequestDtos.stream()
                .map(PlayerRequestDto::toEntity)
                .collect(Collectors.toList()));
    }

    public int size() {
        return players.size();
    }

    public Player get(int index) {
        return players.get(index);
    }

    * */
}