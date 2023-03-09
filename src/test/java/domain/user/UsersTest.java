package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UsersTest {

    Users users;

    @BeforeEach
    void setUpUsers() {
        Map<String, Integer> players = new LinkedHashMap<>();
        players.put("hongo", 1000);
        players.put("kiara", 1000);
        players.put("ash", 1000);
        users = Users.from(players);
    }

    @DisplayName("참여 인원은 1명 이상 4명 이하이다")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void playerCount1_4(Map<String, Integer> players) {
        assertThatNoException()
            .isThrownBy(() -> Users.from(players));
    }

    static Stream<Map<String, Integer>> parameterProvider() {
        return Stream.of(
            Map.of("hongo", 1000, "kiara", 1000, "ash", 1000, "woowa", 1000),
            Map.of("ash", 1000)
        );
    }

    @DisplayName("참여 인원은 1명미만이 될 수 없다")
    @Test
    void playerCount_shouldNotBeUnder1() {
        assertThatThrownBy(() -> Users.from(Collections.emptyMap()))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이상, ");
    }

    @DisplayName("참여 인원은 4명초과가 될 수 없다")
    @Test
    void playerCount_shouldNotBeOver4() {
        assertThatThrownBy(() -> Users.from(Map.of("a", 10, "b", 10, "c", 10, "d", 10, "e", 10)))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이하여야 합니다.");
    }

    @DisplayName("해당 이름의 플레이어에게 카드를 추가한다")
    @Test
    void hitCardByName() {
        users.hitCardByName("hongo", new Card(Denomination.JACK, Suits.HEART));
        Player player = users.getPlayers().get(0);
        assertThat(player.getScore()).isEqualTo(10);
    }

    @DisplayName("카드를 더 받을 수 있는 플레이어 리스트를 반환한다")
    @Test
    void getHittablePlayers() {
        // 카드 현황
        // player1 : X          => 0
        // player2 : 7          => 7
        // player3 : 10, 10, 1  => 21
        users.hitCardByName("ash", new Card(Denomination.SEVEN, Suits.DIAMOND));
        users.hitCardByName("kiara", new Card(Denomination.JACK, Suits.DIAMOND));
        users.hitCardByName("kiara", new Card(Denomination.QUEEN, Suits.DIAMOND));
        users.hitCardByName("kiara", new Card(Denomination.ACE, Suits.DIAMOND));

        List<Player> hittablePlayers = users.getHittablePlayers();
        assertThat(hittablePlayers)
            .satisfiesExactly(
                player -> assertNameEquals(player, "hongo"),
                player -> assertNameEquals(player, "ash"));
    }

    private void assertNameEquals(Player player, String name) {
        assertThat(player.getName()).isEqualTo(name);
    }

}
