package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @DisplayName("각 플레이어 마다 기본 카드 2장을 발급한다")
    @Test
    void give_two_cards() {
        // given
        Player player = new Player("두리");

        // when
        int result = player.getCards().size();

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어의 목록으로 객체를 생성한다")
    void make_players() {
        //given
        List<String> playerNames = List.of("두리", "비타");
        List<Player> playerExpected = List.of(new Player("두리"), new Player("비타"));

        //when
        Players players = new Players(playerNames);

        //then
        assertThat(players.getPlayers()).isEqualTo(playerExpected);
    }
}
