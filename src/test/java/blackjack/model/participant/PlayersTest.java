package blackjack.model.participant;

import static blackjack.TestFixtures.UNSHUFFLED_DECK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어들 테스트")
class PlayersTest {

    @DisplayName("플레이어가 1명 ~ 6명이고 이름이 중복되지 않으면 players를 생성한다")
    @Test
    void createPlayersTest() {
        assertThatCode(() ->Players.from(List.of("test1", "test2", "test3", "test4", "test5")))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어 이름이 중복되면 예외가 발생한다.")
    @Test
    void duplicateNameTest() {
        assertThatCode(() -> Players.from(List.of("pobi", "pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }


    @DisplayName("플레이어가 1명 미만이면 예외가 발생한다.")
    @Test
    void minimumPlayerTest() {
        assertThatCode(() -> Players.from(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가능한 플레이어 슈는 1명 ~ 6명 입니다.");
    }

    @DisplayName("플레이어는 6명 초과이면 예외가 발생한다.")
    @Test
    void maxPlayerTest() {
        assertThatCode(() -> Players.from(List.of("test1", "test2", "test3", "test4", "test5", "test6", "test7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가능한 플레이어 슈는 1명 ~ 6명 입니다.");
    }

    @DisplayName("플레이어들은 초기 2장의 카드를 받는다.")
    @Test
    void dealInitialHandTest() {
        // given
        Players players = Players.from(List.of("test1", "test2"));
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);

        // when
        players.dealInitialHand(dealer);

        // then
        assertThat(players.getPlayers())
                .allSatisfy(player -> assertThat(player.getHand()).hasSize(2));
    }
}