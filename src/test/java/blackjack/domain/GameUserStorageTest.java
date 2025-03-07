package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameUserStorageTest {

    GameUserStorage gameUserStorage;

    @BeforeEach
    void beforeEach() {
        gameUserStorage = new GameUserStorage();
    }

    @Test
    @DisplayName("딜러가 아닌 플레이어를 조회할 수 있다.")
    void canFindPlayer() {
        // given
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameUserStorage.initialize(nicknames);

        // when
        List<Player> actualPlayers = gameUserStorage.getPlayers();

        // then
        assertThat(actualPlayers).extracting(Player::getNickname).extracting(Nickname::getValue)
                .containsExactlyInAnyOrder("쿠키", "빙봉");
    }

    @Test
    @DisplayName("딜러를 조회할 수 있다.")
    void canFindDealer() {
        // given
        List<Nickname> nicknames = List.of(new Nickname("쿠키"), new Nickname("빙봉"));
        gameUserStorage.initialize(nicknames);

        // when
        Player dealer = gameUserStorage.getDealer();

        // then
        assertThat(dealer.getNickname()).isEqualTo(new Nickname("딜러"));
    }

}