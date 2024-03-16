package blackjack.domain.result;

import static blackjack.domain.result.GameResult.BLACKJACK_WIN;
import static blackjack.domain.result.GameResult.PLAYER_LOSE;
import static blackjack.domain.result.GameResult.PLAYER_WIN;
import static blackjack.domain.result.GameResult.PUSH;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.TestHandCreator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.TestPlayerCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("게임 결과 테스트")
class GameResultTest {

    @DisplayName("플레이어 블랙잭이고 딜러가 블랙잭이 아니면 플레이어가 블랙잭 승리를 한다.")
    @Test
    void testPlayerBlackJackWin() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(3, 4));
        assertThat(GameResult.judge(dealer, player)).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("딜러와 플레이어 모두 21점이더라도 블랙잭 핸드가 있다면 블랙잭쪽이 이긴다.")
    @Test
    void testPlayerBlackJackWinMaxScore() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(9, 2, 10));
        assertThat(GameResult.judge(dealer, player)).isEqualTo(BLACKJACK_WIN);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭인 경우 결과는 PUSH이다.")
    @Test
    void testPlayerAndDealerBlackJack() {
        Player player = TestPlayerCreator.of("리비", 1, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(1, 10));
        assertThat(GameResult.judge(dealer, player)).isEqualTo(PUSH);
    }

    @DisplayName("딜러가 버스트 되고 플레이어가 살아있다면 플레이어가 승리한다")
    @Test
    void testPlayerWin() {
        Player player = TestPlayerCreator.of("리비", 10, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(2, 10));
        assertThat(GameResult.judge(dealer, player)).isEqualTo(PLAYER_WIN);
    }

    @DisplayName("딜러 플레이어 모두 버스트 되지 않은 경우 딜러의 점수보다 플레이어의 점수가 낮다면 플레이어가 패한다")
    @Test
    void testPlayerLose() {
        Player player = TestPlayerCreator.of("리비", 2, 10);
        Dealer dealer = new Dealer(TestHandCreator.of(10, 10));
        assertThat(GameResult.judge(dealer, player)).isEqualTo(PLAYER_LOSE);
    }
}
