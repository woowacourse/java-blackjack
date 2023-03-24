package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {
    @DisplayName("입력되는 이름의 수만큼 참가자를 생성한다.")
    @Test
    void Should_Create_When_NewPlayers() {
        Name name1 = new Name("name");
        Name name2 = new Name("name2");
        Name name3 = new Name("name3");

        BettingAmount bettingAmount = new BettingAmount(1000);

        Player player1 = new Player(name1, bettingAmount);
        Player player2 = new Player(name2, bettingAmount);
        Player player3 = new Player(name3, bettingAmount);

        assertThat(new Players(List.of(player1, player2, player3))).isInstanceOf(Players.class);
    }

    @DisplayName("플레이어 수는 1에서 6사이여야 한다.")
    @ParameterizedTest(name = "{displayName} [{index}] => ''{0}''")
    @ValueSource(ints = {0, 7})
    void Should_ThrowException_When_Between1And6(int size) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Player player = new Player(new Name("name"), new BettingAmount(1000));
            players.add(player);
        }

        assertThatThrownBy(() -> new Players(players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("게임을 진행하는 플레이어의 수는 1명에서 6명 사이여야 합니다.");
    }
}
