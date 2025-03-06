package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("딜러가 아닌 플레이어를 조회할 수 있다.")
    void canFindPlayer() {
        // given
        Players players = new Players(List.of(new Nickname("쿠키"), new Nickname("빙봉"), Nickname.createDealerNickname()));

        // when
        List<Player> actualPlayers = players.getPlayers();

        // then
        assertThat(actualPlayers).extracting(Player::getNickname).extracting(Nickname::getValue)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("딜러를 조회할 수 있다.")
    void canFindDealer() {
        // given
        Players players = new Players(List.of(new Nickname("쿠키"), new Nickname("빙봉"), Nickname.createDealerNickname()));

        // when
        Player dealer = players.getDealer();

        // then
        assertThat(dealer.getNickname()).isEqualTo(Nickname.createDealerNickname());
    }

}