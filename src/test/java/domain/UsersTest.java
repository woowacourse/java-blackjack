package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.user.Player;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class UsersTest {

    @DisplayName("참여 인원은 1명 이상 4명 이하이다")
    @ParameterizedTest
    @MethodSource("parameterProvider")
    void playerCount1_4(List<String> names) {
        assertThatNoException()
            .isThrownBy(() -> Users.from(names));
    }

    static Stream<List<String>> parameterProvider() {
        return Stream.of(
            List.of("a"),
            List.of("a", "kiara", "ash", "woowa")
        );
    }

    @DisplayName("참여 인원은 1명미만이 될 수 없다")
    @Test
    void playerCount_shouldNotBeUnder1() {
        assertThatThrownBy(() -> Users.from(Collections.emptyList()))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이상, ");
    }

    @DisplayName("참여 인원은 4명초과가 될 수 없다")
    @Test
    void playerCount_shouldNotBeOver4() {
        assertThatThrownBy(() -> Users.from(List.of("a", "b", "c", "d", "e")))
            .hasMessageContaining("플레이어 수는 ")
            .hasMessageContaining("명 이하여야 합니다.");
    }

    @DisplayName("플레이어의 이름은 중복될 수 없다")
    @Test
    void validateDuplication() {
        assertThatThrownBy(() -> Users.from(List.of("hongo", "hongo", "ash", "kiara")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("해당 이름의 플레이어에게 카드를 추가한다")
    @Test
    void hitCardByName() {
        Users users = Users.from(List.of("hongo", "ash", "kiara"));

        users.hitCardByName("hongo", new Card(Denomination.JACK, Suits.HEART));
        Player player = users.getPlayers().get(0);
        assertThat(player.getScore()).isEqualTo(10);
    }

    @DisplayName("카드를 더 받을 수 있는 플레이어 리스트를 반환한다")
    @Test
    void getHittablePlayers() {
        Users users = Users.from(List.of("hongo", "ash", "kiara"));

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
