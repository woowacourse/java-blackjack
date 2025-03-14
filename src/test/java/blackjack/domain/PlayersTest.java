package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.common.ErrorMessage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @DisplayName("새로운 플레이어들을 이름을 통해서 저장하고 가져온다.")
    @Test
    void test1() {
        // given
        List<String> names = List.of("꾹이", "히로");
        List<Player> playersToBeSaved = names.stream()
                .map(name -> new Player(name, new PlayerHand(new Hand(), Wallet.bet(1000))))
                .toList();

        // when
        Players players = Players.from(playersToBeSaved);

        // then
        assertThat(players.getPlayers()).hasSize(2);
    }

    @DisplayName("플레이어는 7명을 초과할 수 없다.")
    @Test
    void test2() {
        // given
        List<String> names = List.of("듀이", "몽이", "히로", "꾹이", "히포", "비타", "라젤", "서프");
        List<Player> playersToBeSaved = names.stream()
                .map(name -> new Player(name, new PlayerHand(new Hand(), Wallet.bet(1000))))
                .toList();

        // when & then
        assertThatThrownBy(() -> Players.from(playersToBeSaved)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.EXCEED_PLAYER_MEMBERS.getMessage());
    }

    @DisplayName("플레이어가 존재하지 않으면 안된다.")
    @Test
    void test3() {
        List<Player> playersToBeSaved = List.of();

        // when & then
        assertThatThrownBy(() -> Players.from(playersToBeSaved)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NEED_PLAYER_MEMBERS.getMessage());
    }
}
